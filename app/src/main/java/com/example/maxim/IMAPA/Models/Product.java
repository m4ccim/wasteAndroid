package com.example.maxim.IMAPA.Models;

public class Product {
    private int productId;
    private String name;
    private double basePrice;
    private boolean isSoldByWeight;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSoldByWeight() {
        return isSoldByWeight;
    }

    public void setSoldByWeight(boolean soldByWeight) {
        isSoldByWeight = soldByWeight;
    }
}
