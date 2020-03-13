package com.card.utility;

import com.card.model.CardResponse;
import com.card.services.CardService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Created by Tenece on 13/03/2020.
 */

@Component
public class CardDAOImpl implements CardDAO {

    private CardService cardService;

    public CardDAOImpl(CardService cardService) {
        this.cardService = cardService;
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
//        cardResponseMono.subscribe(res -> {
//
//        });
//        cardResponseMono.subscribe(res -> {
//            System.out.println("say ");
//            System.out.println(res);
//        });
        return cardResponseMono;
    }
}
