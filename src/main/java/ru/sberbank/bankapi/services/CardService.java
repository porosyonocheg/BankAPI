package ru.sberbank.bankapi.services;

import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.model.dto.RequestCardDTO;
import ru.sberbank.bankapi.model.dto.ResponseCardDTO;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CardService {
    ResponseCardDTO addNewCard(RequestCardDTO cardDTO) throws  IllegalArgumentException;
    List<ResponseCardDTO> getCards(String accountNumber);
    void addMoney(String number, int value);
    BigDecimal checkBalance(String number) throws IllegalArgumentException;
}
