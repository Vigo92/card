package com.card.services;

import com.card.model.CardDetailResponsePayload;
import com.card.services.util.BinListCardResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Tenece on 13/03/2020.
 */
@Service
public class CardServiceImpl implements CardService {


    private RestTemplate restTemplate;

    @Value("${card.detail.service}")
    private String cardEndpoint;

    public CardServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CardDetailResponsePayload getDetails(String cardNo) {

        try {

            UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(cardEndpoint + "/" + cardNo);
            ResponseEntity<BinListCardResponse> cardMono = restTemplate.getForEntity(parameters.toUriString(), BinListCardResponse.class);

            if (cardMono.getStatusCode() != HttpStatus.OK || !(cardMono.hasBody())) {
                return new CardDetailResponsePayload();
            }

            BinListCardResponse response = cardMono.getBody();

            CardDetailResponsePayload payload = new CardDetailResponsePayload(
                    response.getScheme(),
                    response.getType(),
                    response.getBank().getName()
            );

            return payload;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CardDetailResponsePayload();
    }
}
