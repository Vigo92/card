package com.card.utility;

import com.card.model.CardDetailResponsePayload;
import com.card.model.CardDetails;
import com.card.model.CardReportBase;
import com.card.model.CardResponse;
import com.card.repository.CardDetailsRepository;
import com.card.services.CardService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CardDAOImpl implements CardDAO {

    private CardService cardService;

    private CardDetailsRepository cardDetailsRepository;


    public CardDAOImpl(CardService cardService, CardDetailsRepository cardDetailsRepository) {
        this.cardService = cardService;
        this.cardDetailsRepository = cardDetailsRepository;
    }

    @Override
    public CardResponse getCardDetails(String cardNo) {
        log.info("Processing request for {}", cardNo);
        Optional<CardDetails> cardDetailsOptional = cardDetailsRepository.findTopByCardNumber(cardNo);

        CardDetails cardDetails;

        CardResponse cardResponse = new CardResponse();

        CardDetailResponsePayload payload;

        if (cardDetailsOptional.isPresent()) {

            log.info("{} already exists, increamenting...", cardNo);
            cardDetails = cardDetailsOptional.get();

            cardDetails.setTotalHits(cardDetails.getTotalHits() + 1);

            cardDetailsRepository.save(cardDetails);
            log.info("{} successfully increamentied!", cardNo);

            payload = new CardDetailResponsePayload(cardDetails.getScheme(), cardDetails.getType(), cardDetails.getBank());
            cardResponse.setSuccess(true);
        } else {

            log.info("New card {} recieved, checking 3party for details", cardNo);
            payload = cardService.getDetails(cardNo);
            if (payload.getType().equals("") && payload.getScheme().equals("") && payload.getBank().equals("")) {
                log.info("No response for card {} ", cardNo);
                cardResponse.setSuccess(false);
            } else {
                log.info("Card {} exists in 3rd party, saving it for further nesds...", cardNo);
                cardResponse.setSuccess(true);
                cardDetails = new CardDetails(cardNo, payload.getScheme(), payload.getType(), payload.getBank());
                log.info("No response fore card {} ", cardNo);
                cardDetailsRepository.save(cardDetails);
                log.info("{} successfully saved!", cardNo);
            }
        }

        cardResponse.setPayload(payload);

        return cardResponse;
    }

    @Override
    public CardReportBase getDetails(Integer start, Integer limit) {

        log.info("retrieving from {} to {}", start, limit);
        Pageable pageable = PageRequest.of(start - 1, limit);
        Page<CardDetails> validCards = cardDetailsRepository.findAll(pageable);
        Map<String, Integer> payload = new HashMap<>();
        for (CardDetails cardDetails : validCards.getContent()) {
            payload.put(cardDetails.getCardNumber(), cardDetails.getTotalHits());
        }

        CardReportBase cardReportBase = new CardReportBase(true, start, limit, Long.valueOf(validCards.getTotalElements()).intValue(), payload);

        return cardReportBase;
    }
}
