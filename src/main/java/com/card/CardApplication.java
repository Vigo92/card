package com.card;

import com.card.channels.MessageStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableBinding(MessageStream.class)
public class CardApplication {


    public static void main(String[] args) {
        SpringApplication.run(CardApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        return restTemplateBuilder.setConnectTimeout(Duration.ofMinutes(1)).setReadTimeout(Duration.ofMinutes(2)).build();
    }
}
