package com.example.freshmart.Controller;

import com.example.freshmart.Entity.Cart;
import com.example.freshmart.Entity.CartRequest;
import com.example.freshmart.Services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    // 1️ Add product to cart
    // @PostMapping("/add")
    // public ResponseEntity<?> addToCart(@RequestParam Integer userId,
    //                                    @RequestParam Integer productId,
    //                                    @RequestParam Double quantityKg) {
    //     Cart cartItem = cartServices.addToCart(userId, productId, quantityKg);
    //     if(cartItem == null)
    //         return ResponseEntity.badRequest().body("Invalid user or product");
    //     return ResponseEntity.ok(cartItem);
    // }

    @PostMapping("/add")
public ResponseEntity<?> addToCart(@RequestBody CartRequest request) {
    Cart cartItem = cartServices.addToCart(request.getUserId(), request.getProductId(), request.getQuantityKg());
    if(cartItem == null)
        return ResponseEntity.badRequest().body("Invalid user or product");
    return ResponseEntity.ok(cartItem);
}


    // 2️ Get all cart items for user
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserCart(@PathVariable Integer userId) {
        List<Cart> items = cartServices.getUserCart(userId);
        return ResponseEntity.ok(items);
    }

    // 3️ Update cart item quantity
    @PutMapping("/update/{cartId}")
    public ResponseEntity<?> updateCart(@PathVariable Integer cartId,
                                        @RequestParam Double quantityKg) {
        Cart updated = cartServices.updateCartItem(cartId, quantityKg);
        if(updated == null)
            return ResponseEntity.badRequest().body("Cart item not found");
        return ResponseEntity.ok(updated);
    }

    // 4️ Delete cart item
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable Integer cartId) {
        boolean deleted = cartServices.deleteCartItem(cartId);
        if(!deleted)
            return ResponseEntity.badRequest().body("Cart item not found");
        return ResponseEntity.ok("Cart item deleted successfully");
    }
}
