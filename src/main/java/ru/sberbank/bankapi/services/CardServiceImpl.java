package ru.sberbank.bankapi.services;

import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.model.Account;
import ru.sberbank.bankapi.model.Card;
import ru.sberbank.bankapi.model.dto.RequestCardDTO;
import ru.sberbank.bankapi.model.dto.ResponseCardDTO;
import ru.sberbank.bankapi.repository.AccountRepository;
import ru.sberbank.bankapi.repository.CardDAOImpl;
import ru.sberbank.bankapi.repository.CardRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    private AccountRepository accountRepository;
    private CardDAOImpl cardDAO;

    public CardServiceImpl(AccountRepository accountRepository, CardRepository cardRepository, CardDAOImpl cardDAO) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.cardDAO = cardDAO;
    }

    //TODO: добавить более строгую типизацию карт и проверку типов
    @Override
    public ResponseCardDTO addNewCard(RequestCardDTO cardDTO) throws IllegalArgumentException {
        if (cardDTO == null || cardDTO.getNumber() == null || cardDTO.getPayment() == null || cardDTO.getType() == null) throw new IllegalArgumentException("There are null arguments");
        String number = cardDTO.getNumber();
        if (!isNumberUnique(number)) throw new IllegalArgumentException("The number of the card already exists");
        Card newCard = new Card();
        newCard.setNumber(number);
        newCard.setType(cardDTO.getType());
        newCard.setPayment(cardDTO.getPayment());
        newCard.setAmount(new BigDecimal("0.00"));
        Account ac = accountRepository.findAccountByNumber(cardDTO.getAccount()); // находим счёт, для которого создавалась карта
        if (ac == null) throw new IllegalArgumentException("The account has not found");
        newCard.setAccount(ac);
        cardRepository.save(newCard);
        ResponseCardDTO responseCard = new ResponseCardDTO();
        responseCard.setID(newCard.getID());
        responseCard.setNumber(number);
        return responseCard;
    }

    private boolean isNumberUnique(String number) {
        List<Card> cards = cardRepository.findAll();
        for (Card c : cards) if (c.getNumber().equals(number)) return false;
        return true;
    }

    @Override
    public List<ResponseCardDTO> getCards(String account) {
        MappingUtils mappingUtils = new MappingUtils();
         List<Card> cards = cardDAO.getList(account);
        return cards.stream().map(mappingUtils::mapToResponseCardDto).collect(Collectors.toList());
    }

    @Override
    public void addMoney(String number, int value) {

    }

    @Override
    public BigDecimal checkBalance(String number) throws IllegalArgumentException {
        Card card = cardRepository.findCardByNumber(number);
        if (card == null) throw new IllegalArgumentException("The card was not found");
        return card.getAmount();
    }
}
