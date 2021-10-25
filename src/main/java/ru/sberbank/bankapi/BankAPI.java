package ru.sberbank.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAPI {
    public static void main(String[] args) {
        try {
            H2Server.run();
            SpringApplication.run(BankAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
