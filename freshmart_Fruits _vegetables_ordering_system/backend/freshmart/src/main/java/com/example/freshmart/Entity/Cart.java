// package com.example.freshmart.Entity;

// import jakarta.persistence.*;
// import lombok.Data;

// @Data
// @Entity(name = "cart_items")
// public class Cart {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private Register user;      // User who added item

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     private Product product;    // Product added to cart

//     private Double quantityKg;  // Quantity user wants
//     private Double totalPrice;  // quantityKg * product.pricePerKg
// }





package com.example.freshmart.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items") // make sure table name matches DB
public class Cart {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Register user;      // User who added the item

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;    // Product added to cart

    @Column(nullable = false)
    private Double quantityKg;  // Quantity user wants

    @Column(nullable = false)
    private Double totalPrice;  // quantityKg * product.pricePerKg
}
