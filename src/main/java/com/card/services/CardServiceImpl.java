package com.card.services;

import com.card.model.CardDetailResponseBase;
import com.card.model.CardResponse;
import com.card.services.util.BinListCardResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Created by Tenece on 13/03/2020.
 */
@Service
public class CardServiceImpl implements CardService {

    private WebClient webClient;

    private RestTemplate restTemplate;


    @Value("${card.detail.service}")
    private String cardEndpoint;

    public CardServiceImpl(WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

//    @Override
//    public Mono<CardResponse> getDetails(String cardNo) {
//
//        Mono<BinListCardResponse> cardMono = webClient
//                .get()
//                .uri("/{id}", cardNo)
//                .retrieve()
//                .bodyToMono(BinListCardResponse.class);
//        CardResponse response = new CardResponse();
//        cardMono.subscribe(res -> {
//            System.out.println("say ");
//            System.out.println(res);
//            CardDetailResponseBase payload = new CardDetailResponseBase();
//            payload.setBank(res.getBank().getName());
//            payload.setScheme(res.getScheme());
//            payload.setType(res.getType());
//            response.setSuccess(true);
//            response.setPayload(payload);
//        });
//
//        return Mono.just(response);
//    }

    @Override
    public CardResponse getDetails(String cardNo) {

        UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(cardEndpoint + "/" + cardNo);
        ResponseEntity<BinListCardResponse> cardMono = restTemplate.getForEntity(parameters.toUriString(), BinListCardResponse.class);

        CardResponse response = new CardResponse();
        CardDetailResponseBase payload = new CardDetailResponseBase();
        payload.setBank(cardMono.getBody().getBank().getName());
        payload.setScheme(cardMono.getBody().getScheme());
        payload.setType(cardMono.getBody().getType());
        response.setSuccess(true);
        response.setPayload(payload);
//        cardMono.subscribe(res -> {
//            System.out.println("say ");
//            System.out.println(res);
//            CardDetailResponseBase payload = new CardDetailResponseBase();
//            payload.setBank(res.getBank().getName());
//            payload.setScheme(res.getScheme());
//            payload.setType(res.getType());
//            response.setSuccess(true);
//            response.setPayload(payload);
//        });

        return response;
    }
}
