package com.example.maxim.IMAPA.Models;

public class Card {
    private int cardId;
    private String email;
    private String passwordHash;
    private String cardOwnerName;
    private double balance;

    public int getCardId() {
        return cardId;
    }

    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswordHash() {return passwordHash;}
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getCardOwnerName() {return cardOwnerName;}
    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }
    public double getBalance() {return balance;}
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
