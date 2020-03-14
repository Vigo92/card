package com.card.controller;

import com.card.channels.KafkaProducer;
import com.card.model.CardReportBase;
import com.card.model.CardResponse;
import com.card.services.CardService;
import com.card.utility.CardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by Tenece on 13/03/2020.
 */

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardDAO cardDAO;

    private final KafkaProducer kafkaProducer;

//    public CardController(CardDAO cardDAO) {
//        this.cardDAO = cardDAO;
//    }

//    @GetMapping("/card-scheme/verify/{cardNo}")
//    public Mono<CardResponse> getCardDetails(@PathVariable("cardNo") String cardNo){
//
//        Mono<CardResponse> cardResponseMono = cardDAO.getDetails(cardNo);
//
//        return cardResponseMono;
//    }

    @GetMapping("/card-scheme/verify/{cardNo}")
    public ResponseEntity<CardResponse> getCardDetails(@PathVariable("cardNo") String cardNo){

//        CardResponse cardResponseMono = kafkaProducer.getCardDetails(cardNo);
        kafkaProducer.getCardDetails(cardNo);

//        return new ResponseEntity(cardResponseMono, HttpStatus.OK);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @GetMapping("/card-scheme/stats")
    public ResponseEntity<CardReportBase> getCardDetails(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "limit", defaultValue = "3") Integer limit){

        CardReportBase cardResponseMono = cardDAO.getDetails(start, limit);

        return new ResponseEntity(cardResponseMono, HttpStatus.OK);
    }
}
