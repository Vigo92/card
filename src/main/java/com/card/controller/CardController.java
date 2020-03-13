package com.card.controller;

import com.card.model.CardResponse;
import com.card.services.CardService;
import com.card.utility.CardDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by Tenece on 13/03/2020.
 */

@RestController
public class CardController {

    private CardDAO cardDAO;

    public CardController(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

//    @GetMapping("/card-scheme/verify/{cardNo}")
//    public Mono<CardResponse> getCardDetails(@PathVariable("cardNo") String cardNo){
//
//        Mono<CardResponse> cardResponseMono = cardDAO.getDetails(cardNo);
//
//        return cardResponseMono;
//    }

    @GetMapping("/card-scheme/verify/{cardNo}")
    public ResponseEntity<CardResponse> getCardDetails(@PathVariable("cardNo") String cardNo){

        CardResponse cardResponseMono = cardDAO.getDetails(cardNo);

        return new ResponseEntity(cardResponseMono, HttpStatus.OK);
    }
}
