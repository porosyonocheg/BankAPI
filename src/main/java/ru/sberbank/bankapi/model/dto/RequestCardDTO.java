package ru.sberbank.bankapi.model.dto;

public class RequestCardDTO {
private String number;
private String type;
private String payment;
private String account;

    public String getAccount() {
        return account;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getPayment() {
        return payment;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
