package com.example.maxim.IMAPA.Models;

public class BillProductWaste {

    private int id;
    private int billProductId;
    private BillProduct billProduct;
    private int wasteId;
    private com.example.maxim.IMAPA.Models.Waste waste;
    private double discountedAmount;

    public void setId(int id) {
        this.id = id;
    }

    public void setWasteId(int wasteId) {
        this.wasteId = wasteId;
    }

    public void setWaste(com.example.maxim.IMAPA.Models.Waste waste) {
        this.waste = waste;
    }

    public void setBillProduct(com.example.maxim.IMAPA.Models.BillProduct billProduct) {
        this.billProduct = billProduct;
    }

    public void setBillProductId(int billProductId) {
        this.billProductId = billProductId;
    }

    public void setDiscountedAmount(double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public int getId() {
        return id;
    }

    public int getWasteId() {
        return wasteId;
    }

    public com.example.maxim.IMAPA.Models.Waste getWaste() {
        return waste;
    }

    public com.example.maxim.IMAPA.Models.BillProduct getBillProduct() {
        return billProduct;
    }

    public double getDiscountedAmount() {
        return discountedAmount;
    }

    public int getBillProductId() {
        return billProductId;
    }

}
