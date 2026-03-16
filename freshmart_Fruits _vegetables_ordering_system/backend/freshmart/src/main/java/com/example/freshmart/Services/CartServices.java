package com.example.freshmart.Services;

import com.example.freshmart.Entity.Cart;
import com.example.freshmart.Entity.Product;
import com.example.freshmart.Entity.Register;
import com.example.freshmart.Repository.CartRepository;
import com.example.freshmart.Repository.ProductRepository;
import com.example.freshmart.Repository.RegisterRepository; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RegisterRepository registerRepository; // <-- replace userRepository

    // Add product to cart
    public Cart addToCart(Integer userId, Integer productId, Double quantityKg) {
        Register user = registerRepository.findById(userId).orElse(null); // <-- changed
        Product product = productRepository.findById(productId).orElse(null);

        if(user == null || product == null) return null;

        Cart cartItem = new Cart();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantityKg(quantityKg);
        cartItem.setTotalPrice(quantityKg * product.getPricePerKg());

        return cartRepository.save(cartItem);
    }

    // Get all cart items for a user
    public List<Cart> getUserCart(Integer userId) {
        Register user = registerRepository.findById(userId).orElse(null); // <-- changed
        if(user == null) return null;
        return cartRepository.findByUser(user);
    }

    // Update quantity of a cart item
    public Cart updateCartItem(Integer cartId, Double quantityKg) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null) return null;

        cart.setQuantityKg(quantityKg);
        cart.setTotalPrice(quantityKg * cart.getProduct().getPricePerKg());

        return cartRepository.save(cart);
    }

    // Delete cart item
    public boolean deleteCartItem(Integer cartId) {
        if(cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return true;
        }
        return false;
    }
}
