package com.example.warehouseinventory;

public class Product {
    private String skuId; // Added SKU ID field
    private String name;
    private int stock;

    // Updated constructor to include SKU ID
    public Product(String skuId, String name, int stock) {
        this.skuId = skuId;
        this.name = name;
        this.stock = stock;
    }

    // Getter for SKU ID
    public String getSkuId() {
        return skuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}