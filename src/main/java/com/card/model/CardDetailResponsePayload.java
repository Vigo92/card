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
public class CardDetailResponsePayload {

    private String scheme = "";

    private String type = "";

    private String bank = "";

    public CardDetailResponsePayload() {
    }

    public CardDetailResponsePayload(String scheme, String type, String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }
}
