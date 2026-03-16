package com.example.freshmart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Feedback;
import com.example.freshmart.Entity.Product;
import com.example.freshmart.Entity.Register;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

        // Get all feedbacks of a user
    List<Feedback> findByUser(Register user);

    // Get all feedbacks of a product
    List<Feedback> findByProduct(Product product);

    
}
