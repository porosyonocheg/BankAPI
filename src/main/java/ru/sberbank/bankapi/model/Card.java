package ru.sberbank.bankapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**Java Bean, представляющий сущность "Карта"*/
@Entity
@Table(name = "CARDS")
public class Card {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ACCOUNTID", referencedColumnName ="ID")
    private Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Column(name = "NUMB")
    private String number;

    @Column(name = "SORT")
    private String type;

    @Column(name = "PAYMENT")
    private String payment;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public Account getAccount() { return account; }

    public void setAccount(Account account) {this.account = account;}

    public Long getAccountID() {
        return account.getID();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "account=" + account +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", payment='" + payment + '\'' +
                ", amount=" + amount +
                '}';
    }
}
