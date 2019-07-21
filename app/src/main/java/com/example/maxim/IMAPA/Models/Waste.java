package com.example.maxim.IMAPA.Models;

public class Waste {

    private int wasteId;
    private String name;
    private double recyclingPrice;
    private double startAmount;

    public int getWasteId() {
        return wasteId;
    }

    public void setWasteId(int wasteId) {
        this.wasteId = wasteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRecyclingPrice() {
        return recyclingPrice;
    }

    public void setRecyclingPrice(double recyclingPrice) {
        this.recyclingPrice = recyclingPrice;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }
}
