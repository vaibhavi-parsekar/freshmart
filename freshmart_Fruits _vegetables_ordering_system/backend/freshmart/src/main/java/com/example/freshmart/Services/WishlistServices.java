package com.example.freshmart.Services;

import com.example.freshmart.Entity.Wishlist;
import com.example.freshmart.Entity.Product;
import com.example.freshmart.Entity.Register;
import com.example.freshmart.Repository.WishlistRepository;
import com.example.freshmart.Repository.ProductRepository;
import com.example.freshmart.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServices {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RegisterRepository registerRepository;

    // Add product to wishlist
    public Wishlist addToWishlist(Integer userId, Integer productId) {
        Register user = registerRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if(user == null || product == null) return null;

        // Prevent duplicate entries
        if(wishlistRepository.existsByUserIdAndProductId(userId, productId)) return null;

        Wishlist wishlistItem = new Wishlist();
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);

        return wishlistRepository.save(wishlistItem);
    }

    // Get user's wishlist
    public List<Wishlist> getUserWishlist(Integer userId) {
        Register user = registerRepository.findById(userId).orElse(null);
        if(user == null) return null;

        return wishlistRepository.findByUser(user);
    }

    // Remove item from wishlist
    public boolean removeFromWishlist(Integer wishlistId) {
        if(wishlistRepository.existsById(wishlistId)) {
            wishlistRepository.deleteById(wishlistId);
            return true;
        }
        return false;
    }
}
