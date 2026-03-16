package com.example.freshmart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Cart;
import com.example.freshmart.Entity.Register;

public interface CartRepository extends JpaRepository<Cart, Integer> {

     // Get all cart items for a specific user
    List<Cart> findByUser(Register user);
    
}
