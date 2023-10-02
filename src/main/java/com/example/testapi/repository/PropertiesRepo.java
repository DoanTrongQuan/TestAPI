package com.example.testapi.repository;

import com.example.testapi.models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepo extends JpaRepository<Properties, Integer> {
}
