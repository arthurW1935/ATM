package com.sst.lld.atm.model;

public class Card {
    private final String cardNumber;
    private int pin;
    private final String accountNumber;
    public Card(String cardNumber, int pin, String accountNumber) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }
    public String getCardNumber() {
        return cardNumber;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}