package ru.sberbank.bankapi.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DAO<T> {
    void save(T entity);
    List<T> getList(String accountNumber);
    T getEntity(String number);
}
