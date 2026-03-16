package com.example.freshmart.Controller;

import com.example.freshmart.Entity.Wishlist;
import com.example.freshmart.Services.WishlistServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistServices wishlistServices;

    // Add product to wishlist
    @PostMapping("/add")
    public ResponseEntity<?> addToWishlist(
            @RequestParam Integer userId,
            @RequestParam Integer productId
    ){
        Wishlist item = wishlistServices.addToWishlist(userId, productId);
        if(item == null) return ResponseEntity.status(400).body("Already in wishlist or invalid IDs");
        return ResponseEntity.ok(item);
    }

    // Get user's wishlist
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getUserWishlist(@PathVariable Integer userId){
        List<Wishlist> wishlist = wishlistServices.getUserWishlist(userId);
        return ResponseEntity.ok(wishlist);
    }

    // Remove item from wishlist
    @DeleteMapping("/remove/{wishlistId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Integer wishlistId){
        boolean removed = wishlistServices.removeFromWishlist(wishlistId);
        if(!removed) return ResponseEntity.status(404).body("Wishlist item not found");
        return ResponseEntity.ok("Removed from wishlist");
    }
}

