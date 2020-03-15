package com.card.channels;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Tenece on 14/03/2020.
 */
public interface MessageStream {

    String OUTPUT = "output";
    String INPUT = "input";

    @Output(OUTPUT)
    MessageChannel getCardDetails();
}
