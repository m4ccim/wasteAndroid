package com.example.maxim.IMAPA.Models;

public class ProductWaste {

    private int id;
    private int productId;
    private Product product;
    private int wasteId;
    private Waste waste;
    private double amount;

    public com.example.maxim.IMAPA.Models.Waste getWaste() {
        return waste;
    }

    public double getAmount() {
        return amount;
    }

    public int getWasteId() {
        return wasteId;
    }

    public int getProductId() {
        return productId;
    }

    public int getId() {
        return id;
    }

    public com.example.maxim.IMAPA.Models.Product getProduct() {
        return product;
    }

    public void setWaste(com.example.maxim.IMAPA.Models.Waste waste) {
        this.waste = waste;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setWasteId(int wasteId) {
        this.wasteId = wasteId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(com.example.maxim.IMAPA.Models.Product product) {
        this.product = product;
    }
}
