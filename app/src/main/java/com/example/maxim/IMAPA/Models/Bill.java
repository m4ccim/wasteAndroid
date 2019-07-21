package com.example.maxim.IMAPA.Models;

public class Bill {
    public int billId;
    public int cardId;
    public Card card;
    public String dateTime;
    public double discount;
    public double total;


    public int getBillId() {
        return billId;
    }

    public void setBillId(int id) {
        this.billId = billId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int employeeId) {
        this.cardId = cardId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card employee) {
        this.card = card;
    }

    public String getDate() {
        return dateTime;
    }

    public void setDate(String date) {
        this.dateTime = dateTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
