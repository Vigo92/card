package com.card.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Tenece on 13/03/2020.
 */
@Getter
@Setter
@ToString
@Entity
public class ValidCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cardNo;

    private Integer totalHits;

    public ValidCard() {
    }

    public ValidCard(String cardNo, Integer totalHits) {
        this.cardNo = cardNo;
        this.totalHits = totalHits;
    }
}
