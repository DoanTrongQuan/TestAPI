package com.example.testapi.repository;

import com.example.testapi.models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Integer> {
    ProductDetails findByProductPropertyName(String productPropertyName);
}
