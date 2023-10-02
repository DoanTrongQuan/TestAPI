package com.example.testapi.services;

import com.example.testapi.controller.Request;
import com.example.testapi.helper.ObjectResponse;
import com.example.testapi.models.ProductDetails;
import com.example.testapi.models.Products;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductDetailsServices {
    public ResponseEntity<ObjectResponse> displayErrorQuantityProduct(Request request);
    public boolean updateQuantityProductDetail(Products products);
    public List<ProductDetails> displayLastGenerationProductDetails();
}
