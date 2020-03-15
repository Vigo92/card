package com.card.channels;

import com.card.model.CardResponse;
import com.card.utility.CardDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by Tenece on 14/03/2020.
 */
@Slf4j
@Service
public class KafkaProducer {

    private final MessageStream messageStream;

    public KafkaProducer(MessageStream messageStream) {
        this.messageStream = messageStream;
    }

    public void getCardDetails(CardResponse cardDetails) {

        log.info("sending card details {} to the consumer ", cardDetails);

        MessageChannel messageChannel = messageStream.getCardDetails();

        messageChannel.send(MessageBuilder
                .withPayload(cardDetails)
                .setReplyChannelName(MessageStream.INPUT).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

    }


}
