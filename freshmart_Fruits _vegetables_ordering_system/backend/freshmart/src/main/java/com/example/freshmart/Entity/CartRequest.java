package com.example.freshmart.Entity;

import lombok.Data;

@Data
public class CartRequest {
    private Integer userId;
    private Integer productId;
    private Double quantityKg;
}
