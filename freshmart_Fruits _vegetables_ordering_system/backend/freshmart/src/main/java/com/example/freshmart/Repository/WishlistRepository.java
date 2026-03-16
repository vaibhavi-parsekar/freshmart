package com.example.freshmart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Register;
import com.example.freshmart.Entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {


     
    // Get all wishlist items for a specific user
    List<Wishlist> findByUser(Register user);
    
    // Check if a specific product is already in the user's wishlist
    boolean existsByUserIdAndProductId(Integer userId, Integer productId);
    
}
