package com.card.repository;

import com.card.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Tenece on 15/03/2020.
 */
public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {

    Optional<CardDetails> findTopByCardNumber(String cardNumber);
}
