package com.card.channels;

import com.card.model.CardResponse;
import com.card.utility.CardDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * Created by Tenece on 14/03/2020.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaProducer {

    private final CardDAO cardDAO;

    @StreamListener(Sink.INPUT)
    //@SendTo(Source.OUTPUT)
    public void getCardDetails(String cardNo) {

        System.out.println("say");
        log.debug("sending card details {} to the consumer", cardNo);
//        return cardDAO.getDetails(cardNo);
        cardDAO.getDetails(cardNo);
    }
}
