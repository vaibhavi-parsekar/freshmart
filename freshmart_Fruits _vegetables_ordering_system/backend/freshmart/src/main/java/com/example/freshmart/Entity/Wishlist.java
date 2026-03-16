
package com.example.freshmart.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Register user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
