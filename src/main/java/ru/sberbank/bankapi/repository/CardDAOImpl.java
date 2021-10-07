package ru.sberbank.bankapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.bankapi.model.Account;
import ru.sberbank.bankapi.model.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CardDAOImpl implements DAO<Card> {

    @PersistenceContext
    private EntityManager em;
    public void save(Card entity) {
            em.persist(entity);
    }

    @Transactional
    public List<Card> getList(String accountNumber) {
        TypedQuery<Card> q = em.createQuery("SELECT c FROM Card c WHERE c.account.number=:accountNumber", Card.class);
        q.setParameter("accountNumber", accountNumber);
        List<Card> cards = q.getResultList();
        return cards;
    }

@Transactional
    @Override
    public Card getEntity(String cardNumber) {
            TypedQuery<Card> query = em.createQuery("SELECT '*' FROM Card WHERE number = :number",
                    Card.class);
            query.setMaxResults(1);
            query.setParameter("number", cardNumber);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.getSingleResult();
    }
}
