package com.example.freshmart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Order;
import com.example.freshmart.Entity.Register;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    
    // Get all orders for a specific user
    List<Order> findByUser(Register user);

    List<Order> findByUserId(Integer userId);


    // Fetch last 5 orders for dashboard
List<Order> findTop5ByOrderByIdDesc();

    
}
