package ru.sberbank.bankapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.bankapi.model.Account;
import ru.sberbank.bankapi.services.AccountService;
/*
@RestController
@RequestMapping("/api/accounts/")*/
public class AccountController {
    private AccountService accountService;

}
