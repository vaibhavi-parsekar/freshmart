

// package com.example.freshmart.Entity;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;

// @Data
// @Entity(name = "payments")
// public class Payment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     @ManyToOne
//     @JoinColumn(name = "order_id")
//     private Order order;

//     private String paymentMethod; // COD or Online
//     private Double amount;
//     private String status;        // Pending, Success, Failed
//     private LocalDateTime paymentDate;
// }



package com.example.freshmart.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Link to the order

    @Column(nullable = false)
    private String paymentMethod; // COD, Online, UPI, Card, etc.

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status; // Pending, Success, Failed

    private LocalDateTime paymentDate;

    // Constructors
    public Payment() {}

    public Payment(Order order, String paymentMethod, Double amount, String status) {
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.paymentDate = LocalDateTime.now();
    }
}
