package com.card.utility;

import com.card.model.CardReportBase;
import com.card.model.CardResponse;
import com.card.model.ValidCard;
import com.card.repository.ValidCardRepository;
import com.card.services.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Tenece on 13/03/2020.
 */

@Component
public class CardDAOImpl implements CardDAO {

    private CardService cardService;

    private ValidCardRepository cardRepository;

    public CardDAOImpl(CardService cardService, ValidCardRepository cardRepository) {
        this.cardService = cardService;
        this.cardRepository = cardRepository;
    }

//    @Override
//    public Mono<CardResponse> getDetails(String cardNo) {
//
//        Mono<CardResponse> cardResponseMono = cardService.getDetails(cardNo);
//        cardResponseMono.subscribe(res -> {
//
//        });
//cardResponseMono.subscribe(res -> {
//    System.out.println("say ");
//    System.out.println(res);
//});
//        return cardResponseMono;
//    }

    @Override
    public CardResponse getDetails(String cardNo) {

        CardResponse cardResponseMono = cardService.getDetails(cardNo);

        if (cardResponseMono != null) {
            Optional<ValidCard> optional = cardRepository.findOneByCardNo(cardNo);
            if (optional.isPresent()) {
                ValidCard validCards = optional.get();
                validCards.setTotalHits(validCards.getTotalHits() + 1);
                cardRepository.save(validCards);
            } else {
                ValidCard validCard = new ValidCard(cardNo, 1);
                cardRepository.save(validCard);
            }
        }
//        cardResponseMono.subscribe(res -> {
//
//        });
//        cardResponseMono.subscribe(res -> {
//            System.out.println("say ");
//            System.out.println(res);
//        });
        return cardResponseMono;
    }

    @Override
    public CardReportBase getDetails(Integer start, Integer limit) {

        Pageable pageable = PageRequest.of(start - 1, limit);
        Page<ValidCard> validCards = cardRepository.findAll(pageable);
        Map<String, Integer> payload = new HashMap<>();
        for (ValidCard validCard : validCards.getContent()) {
            payload.put(validCard.getCardNo(), validCard.getTotalHits());
        }

        CardReportBase cardReportBase = new CardReportBase(true, start, limit, Long.valueOf(validCards.getTotalElements()).intValue(), payload);

        return cardReportBase;
    }
}
