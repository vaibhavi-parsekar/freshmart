// package com.example.freshmart.Entity;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;
// import java.util.List;

// @Data
// @Entity(name = "orders")
// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private Register user;

//     @OneToMany(cascade = CascadeType.ALL)
//     @JoinColumn(name = "order_id")
//     private List<OrderItem> items;

//     private Double totalAmount;
//     private String status;       // Pending, Confirmed, Delivered
//     private String paymentMethod; // COD / Online
//     private LocalDateTime orderDate;
// }










// package com.example.freshmart.Entity;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.time.LocalDateTime;
// import java.util.List;

// @Data
// @Entity(name = "orders")
// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     // Fetch user immediately when fetching order
//     @ManyToOne(fetch = FetchType.EAGER)
//     @JoinColumn(name = "user_id")
//     private Register user;

//     // Fetch all order items immediately when fetching order
//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     @JoinColumn(name = "order_id")
//     private List<OrderItem> items;

//     private Double totalAmount;
//     private String status;        // Pending, Confirmed, Delivered
//     private String paymentMethod; // COD / Online
//     private LocalDateTime orderDate;
// }









package com.example.freshmart.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Link to user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"orders"}) // prevent recursion in JSON
    private Register user;

    // Link to order items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"order"}) // prevent recursion in JSON
    private List<OrderItem> orderItems;

    private Double totalAmount;
    private String status;        // Pending, Confirmed, Delivered
    private String paymentMethod; // COD / Online
    private LocalDateTime orderDate;
}
