package ru.sberbank.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
        Card findCardByNumber(String number);
}
