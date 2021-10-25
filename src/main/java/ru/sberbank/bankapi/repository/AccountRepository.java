package ru.sberbank.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByNumber(String number);
}
