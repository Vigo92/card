package com.card.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tenece on 14/03/2020.
 */
@Configuration
@EnableBinding({Sink.class, Source.class})
//@EnableBinding(Source.class)
public class KafkaConfig {


}
