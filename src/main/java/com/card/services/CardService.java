package com.card.services;

import com.card.model.CardDetailResponsePayload;
import com.card.model.CardResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by Tenece on 13/03/2020.
 */
public interface CardService {

    CardDetailResponsePayload getDetails(String cardNo);
}
