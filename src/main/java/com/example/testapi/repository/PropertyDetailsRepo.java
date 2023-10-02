package com.example.testapi.repository;

import com.example.testapi.models.ProductDetails;
import com.example.testapi.models.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailsRepo extends JpaRepository<PropertyDetails, Integer> {

}
