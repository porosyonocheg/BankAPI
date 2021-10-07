package ru.sberbank.bankapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.bankapi.model.dto.RequestCardDTO;
import ru.sberbank.bankapi.model.dto.ResponseCardDTO;
import ru.sberbank.bankapi.services.CardService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cards/")
public class CardController {
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "new")
     public ResponseCardDTO addNewCard(@RequestBody RequestCardDTO cardDTO) {
         return cardService.addNewCard(cardDTO);
     }

    @GetMapping(value = "list")
    public List<ResponseCardDTO> getCards(@RequestParam(value = "accountNumber") String accountNumber) {
        return cardService.getCards(accountNumber);
    }

    @GetMapping(value = "balance")
    public HashMap<String,String> getBalance(@RequestParam(value = "cardNumber") String number) {
        HashMap<String, String> response = new HashMap<>();
        response.put("balance", cardService.checkBalance(number).toString());
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
