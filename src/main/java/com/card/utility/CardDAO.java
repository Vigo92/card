package com.card.utility;

import com.card.model.CardResponse;
import reactor.core.publisher.Mono;

/**
 * Created by Tenece on 13/03/2020.
 */
public interface CardDAO {

//    Mono<CardResponse> getDetails(String cardNo);

    CardResponse getDetails(String cardNo);
}
