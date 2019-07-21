package com.example.maxim.IMAPA.Models;

import java.util.List;

public class BillDetails
{
    private Bill bill;
    private List<ProductDetails> productDetails;

    public Bill getBill() {
        return bill;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    public static class WasteDiscount
    {
        private Waste waste;
        private double discountedAmount;

        public double getDiscountedAmount() {
            return discountedAmount;
        }

        public Waste getWaste() {
            return waste;
        }

        public void setDiscountedAmount(double discountedAmount) {
            this.discountedAmount = discountedAmount;
        }

        public void setWaste(Waste waste) {
            this.waste = waste;
        }
    }

    public static class ProductDetails
    {
        private Product product;
        private double amount;
        private List<WasteDiscount> wasteDiscounts;

        public Product getProduct() {
            return product;
        }

        public double getAmount() {
            return amount;
        }

        public List<WasteDiscount> getWasteDiscounts() {
            return wasteDiscounts;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setWasteDiscounts(List<WasteDiscount> wasteDiscounts) {
            this.wasteDiscounts = wasteDiscounts;
        }
    }
}

