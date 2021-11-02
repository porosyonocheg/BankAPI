package ru.sberbank.bankapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.bankapi.model.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CardDAOImpl implements DAO<Card> {

    @PersistenceContext
    private EntityManager em;

    /**Сохранение карты в базу данных*/
    @Transactional
    public void save(Card entity) {
            em.persist(entity);
    }

    /**Получение списка всех карт из базы данных*/
    @Transactional
    public List<Card> findAll() {
        return em.createQuery("SELECT c FROM Card c", Card.class).getResultList();
    }

    /**Получение списка всех карт для переданного номера счёта*/
    @Transactional(readOnly = true)
    public List<Card> getListByAccountNumber(String accountNumber) {
            TypedQuery<Card> q = em.createQuery("SELECT c FROM Card c WHERE c.account.number=:accountNumber", Card.class);
            q.setParameter("accountNumber", accountNumber);
            List<Card> cards = q.getResultList();
            return cards;
    }

    /**Поиск карты в базе данных по переданному номеру карты*/
    @Transactional(readOnly = true)
    public Card findByNumber(String cardNumber) {
            TypedQuery<Card> query = em.createQuery("SELECT '*' FROM Card WHERE number = :number",
                    Card.class);
            query.setMaxResults(1);
            query.setParameter("number", cardNumber);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.getSingleResult();
    }

    /**Удаление карты по номеру*/
    @Transactional
    public void deleteCard(String cardNumber) {
        em.createQuery("DELETE FROM Card WHERE number = :number").setParameter("number", cardNumber);
    }
}
