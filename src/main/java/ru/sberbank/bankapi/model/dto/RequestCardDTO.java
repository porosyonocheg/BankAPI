package ru.sberbank.bankapi.model.dto;
import javax.validation.constraints.NotNull;

public class RequestCardDTO {
    @NotNull
    private String number;
    @NotNull
    private String type;
    @NotNull
    private String payment;
    @NotNull
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

    public void setNumber(String number) {this.number = number;}

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
