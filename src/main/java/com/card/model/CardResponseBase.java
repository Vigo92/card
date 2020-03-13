package com.card.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Tenece on 13/03/2020.
 */
@Getter
@Setter
@ToString
public class CardResponseBase {

    private Boolean success;

    CardDetailResponse payload = new CardDetailResponse();
}
