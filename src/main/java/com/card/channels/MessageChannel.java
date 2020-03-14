package com.card.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Tenece on 14/03/2020.
 */
public interface MessageChannel {


    @Input(Sink.INPUT)
    SubscribableChannel getCardDetails();
}
