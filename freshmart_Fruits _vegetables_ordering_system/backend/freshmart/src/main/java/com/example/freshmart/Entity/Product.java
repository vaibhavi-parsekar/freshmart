
// package com.example.freshmart.Entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Transient;
// import lombok.Data;

// @Data
// @Entity(name = "products")
// public class Product {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     private String name;
//     private String category;     // Fruit or Vegetable
//     private String origin;       // Farm/Location
//     private String description;
//     private String image;

//     @Column(name = "price_per_kg")
//     private Double pricePerKg;

//     @Column(name = "stock_kg")
//     private Double stockKg;

//     // Frontend-friendly getters
//     @Transient
//     public Double getPrice() {
//         return pricePerKg;
//     }

//     @Transient
//     public Double getStock() {
//         return stockKg;
//     }
// }








package com.example.freshmart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String category;     // Fruit, Vegetable, Frozen, Cuts & Sprouts, Leafy & Herbs, etc.
    private String origin;       // Farm/Location
    private String description;
    private String image;

    @Column(name = "price_per_kg")
    private Double pricePerKg;   // For kg-based products

    @Column(name = "stock_kg")
    private Double stockKg;

    @Column(name = "price_per_g")
    private Double pricePerGram; // For gram-based products

    @Column(name = "stock_g")
    private Double stockGrams;

    @Column(name = "price_per_piece")
    private Double pricePerPiece; // For piece-based products

    @Column(name = "stock_pcs")
    private Integer stockPieces;

    private String unit; // "kg", "g", "pcs" - defines the unit of the product

    // -----------------------------
    // Frontend-friendly getters
    // -----------------------------
    @Transient
    public Double getPrice() {
        if(unit == null) return 0.0;
        switch (unit.toLowerCase()) {
            case "kg":
                return pricePerKg != null ? pricePerKg : 0.0;
            case "g":
                return pricePerGram != null ? pricePerGram : 0.0;
            case "pcs":
                return pricePerPiece != null ? pricePerPiece : 0.0;
            default:
                return 0.0;
        }
    }

    @Transient
    public String getStockDisplay() {
        if(unit == null) return "0";
        switch (unit.toLowerCase()) {
            case "kg":
                return (stockKg != null ? stockKg : 0.0) + " kg";
            case "g":
                return (stockGrams != null ? stockGrams : 0.0) + " g";
            case "pcs":
                return (stockPieces != null ? stockPieces : 0) + " pcs";
            default:
                return "0";
        }
    }
}
