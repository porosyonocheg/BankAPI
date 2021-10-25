package ru.sberbank.bankapi.model.dto;

public class ResponseCardDTO {
    private long ID;
    private String number;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
