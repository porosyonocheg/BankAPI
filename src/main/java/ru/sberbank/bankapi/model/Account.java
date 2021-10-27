package ru.sberbank.bankapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNTS", indexes = {@Index(name = "IDX_NUMB", columnList = "NUMB")})
public class Account {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<Card> cards = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column (name = "NUMB")
    private String number;

    @Column(name = "SORT")
    private String type;

    @Column (name = "CURRENCY")
    private String currency;

    @Column (name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "VERSION")
    private short version;

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public Long getID() { return ID; }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Set<Card> getCards() {
        return cards;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
