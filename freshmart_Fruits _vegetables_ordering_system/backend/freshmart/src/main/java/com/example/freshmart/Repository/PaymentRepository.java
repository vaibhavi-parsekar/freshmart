// package com.example.freshmart.Repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.Payment;

// public interface PaymentRepository extends JpaRepository<Payment, Integer> {

//      // Get all payments for a specific order
//     List<Payment> findByOrder(Order order);
    
// }




package com.example.freshmart.Repository;

import com.example.freshmart.Entity.Order;
import com.example.freshmart.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // Get all payments for a specific order
    List<Payment> findByOrder(Order order);

    // Optional: get all payments by a specific user via order
    List<Payment> findByOrder_UserId(Integer userId);
}
