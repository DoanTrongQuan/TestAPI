package com.example.testapi.repository;

import com.example.testapi.models.ProductDetailPropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailPropertyDetailsRepo extends JpaRepository<ProductDetailPropertyDetails, Integer> {
}
