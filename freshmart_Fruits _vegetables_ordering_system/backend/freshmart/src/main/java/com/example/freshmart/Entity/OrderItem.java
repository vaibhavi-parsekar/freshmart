// package com.example.freshmart.Entity;

// import jakarta.persistence.*;
// import lombok.Data;

// @Data
// @Entity(name = "order_items")
// public class OrderItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     private Product product;

//     private Double quantityKg;
//     private Double pricePerKg;
//     private Double totalPrice;
// }







// package com.example.freshmart.Entity;

// import jakarta.persistence.*;
// import lombok.Data;

// @Data
// @Entity(name = "order_items")
// public class OrderItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     // Fetch product immediately when fetching order item
//     @ManyToOne(fetch = FetchType.EAGER)
//     @JoinColumn(name = "product_id")
//     private Product product;

//     private Double quantityKg;
//     private Double pricePerKg;
//     private Double totalPrice;
// }








package com.example.freshmart.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Link to Product
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private Double quantityKg;
    private Double pricePerKg;
    private Double totalPrice;

    // Link to Order (parent)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties({"orderItems"}) // prevent recursion
    private Order order;
}
