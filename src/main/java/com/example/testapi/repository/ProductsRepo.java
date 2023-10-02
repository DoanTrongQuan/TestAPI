package com.example.testapi.repository;

import com.example.testapi.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
}
