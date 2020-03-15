package com.card.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Tenece on 15/03/2020.
 */
@Getter
@Setter
@ToString
@Entity
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String scheme;

    private String type;

    private String bank;

    private Integer totalHits;

    public CardDetails() {
    }

    public CardDetails(String cardNumber, String scheme, String type, String bank) {
        this.cardNumber = cardNumber;
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
        this.totalHits = 1;
    }
}
