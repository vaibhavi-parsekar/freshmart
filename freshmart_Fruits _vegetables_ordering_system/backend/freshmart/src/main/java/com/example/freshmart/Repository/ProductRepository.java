package com.example.freshmart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{


    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findByOriginIgnoreCase(String origin);
    
}
