package ru.sberbank.bankapi.services;

import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.model.Account;
import ru.sberbank.bankapi.model.Card;
import ru.sberbank.bankapi.model.dto.RequestCardDTO;
import ru.sberbank.bankapi.model.dto.ResponseCardDTO;
import ru.sberbank.bankapi.repository.AccountRepository;
import ru.sberbank.bankapi.repository.CardDAOImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private final AccountRepository accountRepository;
    private final CardDAOImpl cardDAO;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CardServiceImpl.class.getName());

    public CardServiceImpl(AccountRepository accountRepository, CardDAOImpl cardDAO) {
        this.accountRepository = accountRepository;
        this.cardDAO = cardDAO;
    }

    //TODO: добавить более строгую типизацию карт и проверку типов
    /**Добавление новой карты
     * @param cardDTO содержит данные для создания новой карты: номер, тип, платежную систему и номер счёта*/
    @Override
    public ResponseCardDTO addNewCard(RequestCardDTO cardDTO) throws IllegalArgumentException, ru.sberbank.bankapi.exceptions.EntityNotFoundException {
        String number = cardDTO.getNumber();
        log.info("We got data for a new card with number: " + number);
        if (!isNumberUnique(number)) {
            log.error("The number of the card already exists");
            throw new IllegalArgumentException("The number of the card already exists");
        }

        Card newCard = new Card();
        newCard.setNumber(number);
        newCard.setType(cardDTO.getType());
        newCard.setPayment(cardDTO.getPayment());
        newCard.setAmount(new BigDecimal("0.00"));

        Account ac = accountRepository.findAccountByNumber(cardDTO.getAccount()); // находим счёт, для которого создавалась карта
        if (ac == null) {
            log.error("The account has not found");
            throw new ru.sberbank.bankapi.exceptions.EntityNotFoundException("The account has not found");
        }

        newCard.setAccount(ac);
        cardDAO.save(newCard);
        ResponseCardDTO responseCard = new ResponseCardDTO();
        responseCard.setID(newCard.getID());
        responseCard.setNumber(number);
        log.info("Card#" + number + " was created");
        return responseCard;
    }

    /**Проверка уникальности номера карты*/
    private boolean isNumberUnique(String number) {
        List<Card> cards = cardDAO.findAll();
        for (Card c : cards) {
            if (c.getNumber().equals(number)) {
                return false;
            }
        }
        return true;
    }

    /**Получение списка всех карт по номеру счёта
     *@param account номер искомого счёта */
    @Override
    public List<ResponseCardDTO> getCards(String account) {
        MappingUtils mappingUtils = new MappingUtils();
        List<Card> cards = cardDAO.getListByAccountNumber(account);
        return cards.stream().map(mappingUtils::mapToResponseCardDto).collect(Collectors.toList());
    }

    /**Внесение денежных средств на карту
     *@param number номер искомой карты
     *@param value сумма для добавления
     *@throws  ru.sberbank.bankapi.exceptions.EntityNotFoundException если карта с таким номером не найдена в базе*/
    @Override
    public void addMoney(String number, int value) {
        Card card = checkCard(number);
        synchronized (cardDAO) {
            card.setAmount(card.getAmount().add(new BigDecimal(value)));
            cardDAO.save(card);
        }
        log.info("Amount " + value + " was successful added\nNew balance for the card#" + number + " is " + card.getAmount());
    }

    /**Проверка существования в базе данных карты по переданному номеру
     * @return сущность карты, если она существует*/
    private Card checkCard(String number) {
        log.info("We are searching for a card#" + number);
        Card card = cardDAO.findByNumber(number);
        if (card == null) {
            log.error("The card was not found");
            throw new ru.sberbank.bankapi.exceptions.EntityNotFoundException("The card was not found");
        }
        log.info("The card was found");
        return card;
    }

    /**Проверка баланса по номеру карты
     * @param number номер запрашиваемой карты
     * @throws ru.sberbank.bankapi.exceptions.EntityNotFoundException если карта с таким номером не найдена в базе*/
    @Override
    public BigDecimal checkBalance(String number) throws ru.sberbank.bankapi.exceptions.EntityNotFoundException {
        Card card = checkCard(number);
        return card.getAmount();
    }
}
