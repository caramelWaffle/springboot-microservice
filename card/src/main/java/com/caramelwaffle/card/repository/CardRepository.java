package com.caramelwaffle.card.repository;

import com.caramelwaffle.card.entity.CardEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByCardId(Long cardId);
}
