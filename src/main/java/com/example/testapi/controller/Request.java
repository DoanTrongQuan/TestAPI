package com.example.testapi.controller;

import com.example.testapi.models.Products;

public class Request {
    private Products products;
    private int quantityBuy;


    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }
}
