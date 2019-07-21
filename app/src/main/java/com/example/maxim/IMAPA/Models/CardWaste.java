package com.example.maxim.IMAPA.Models;

public class CardWaste {

    private int cardWasteId;
    private int cardId;
    private Card card;
    private int wasteId;
    private com.example.maxim.IMAPA.Models.Waste waste;
    private double amount;

    public int getWasteId() {
        return wasteId;
    }

    public com.example.maxim.IMAPA.Models.Card getCard() {
        return card;
    }

    public double getAmount() {
        return amount;
    }

    public int getCardId() {
        return cardId;
    }

    public int getCardWasteId() {
        return cardWasteId;
    }

    public com.example.maxim.IMAPA.Models.Waste getWaste() {
        return waste;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCard(com.example.maxim.IMAPA.Models.Card card) {
        this.card = card;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setCardWasteId(int cardWasteId) {
        this.cardWasteId = cardWasteId;
    }

    public void setWaste(com.example.maxim.IMAPA.Models.Waste waste) {
        this.waste = waste;
    }

    public void setWasteId(int wasteId) {
        this.wasteId = wasteId;
    }
}
