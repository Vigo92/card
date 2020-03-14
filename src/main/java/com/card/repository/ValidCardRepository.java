package com.card.repository;

import com.card.model.ValidCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by Tenece on 13/03/2020.
 */
public interface ValidCardRepository extends JpaRepository<ValidCard, Long>{

    @Query("UPDATE ValidCard v set v.totalHits = v.totalHits + 1 WHERE v.cardNo = :cardNo")
    void updateCount(@Param("cardNo") String cardNo);

    Optional<ValidCard> findOneByCardNo(String cardNo);
}
