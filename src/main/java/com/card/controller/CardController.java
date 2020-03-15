package com.card.controller;

import com.card.channels.KafkaProducer;
import com.card.model.CardReportBase;
import com.card.model.CardResponse;
import com.card.utility.CardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tenece on 13/03/2020.
 */

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardDAO cardDAO;

    private final KafkaProducer kafkaProducer;


    @GetMapping("/card-scheme/verify/{cardNo}")
    public ResponseEntity getCardDetails(@PathVariable("cardNo") String cardNo) {

        CardResponse cardResponse = cardDAO.getCardDetails(cardNo);
        kafkaProducer.getCardDetails(cardResponse);
        return new ResponseEntity(cardResponse, HttpStatus.OK);
    }

    @GetMapping("/card-scheme/stats")
    public ResponseEntity<CardReportBase> getCardDetails(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "limit", defaultValue = "3") Integer limit) {

        CardReportBase cardResponseMono = cardDAO.getDetails(start, limit);

        return new ResponseEntity(cardResponseMono, HttpStatus.OK);
    }
}
