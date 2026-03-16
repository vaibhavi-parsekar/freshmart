
// package com.example.freshmart.Services;
// import com.example.freshmart.Entity.Product;
// import com.example.freshmart.Repository.ProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Collection;
// import java.util.List;

// @Service
// public class ProductServices {

//     @Autowired
//     private ProductRepository productRepository;

//     // 1️ Create new product
//     public Product createProduct(Product productData) {
//         return productRepository.save(productData);
//     }

//     // 2️ Get all products
//     public Collection<Product> getAllProducts() {
//         return productRepository.findAll();
//     }

//     // 3️ Get product by ID
//     public Product getById(Integer id) {
//         return productRepository.findById(id).orElse(null);
//     }

//     // 4️ Delete product by ID
//     public void deleteById(Integer id) {
//         productRepository.deleteById(id);
//     }

//     // 5️ Update product by ID
//     public Product updateProduct(Integer id, Product productData) {
//         productData.setId(id); // Ensure the ID remains same
//         return productRepository.save(productData);
//     }

//     // 6️ Search products by name (contains)
//     public List<Product> searchByName(String keyword) {
//         return productRepository.findByNameContainingIgnoreCase(keyword);
//     }

//     // 7️ Filter products by category (Fruit/Vegetable)
//     public List<Product> filterByCategory(String category) {
//         return productRepository.findByCategoryIgnoreCase(category);
//     }

//     // 8️ Filter products by origin/farm
//     public List<Product> filterByOrigin(String origin) {
//         return productRepository.findByOriginIgnoreCase(origin);
//     }
// }











package com.example.freshmart.Services;

import com.example.freshmart.Entity.Product;
import com.example.freshmart.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    // 1️⃣ Create new product
    public Product createProduct(Product productData) {
        return productRepository.save(productData);
    }

    // 2️⃣ Get all products
    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 3️⃣ Get product by ID
    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    // 4️⃣ Delete product by ID
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    // 5️⃣ Update product by ID
    public Product updateProduct(Integer id, Product productData) {
        productData.setId(id); // Ensure the ID remains same
        return productRepository.save(productData);
    }

    // 6️⃣ Search products by name (contains)
    public List<Product> searchByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // 7️⃣ Filter products by category (exact match)
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // 8️⃣ Filter products by origin/farm
    public List<Product> filterByOrigin(String origin) {
        return productRepository.findByOriginIgnoreCase(origin);
    }

    // 9️⃣ Reduce stock when an order is successful
    public void reduceStock(Integer productId, Double quantity) {
        Product product = getById(productId);
        if (product != null) {
            Double currentStock = product.getStockKg();  // Use stockKg directly
            if (currentStock >= quantity) {
                product.setStockKg(currentStock - quantity);
                productRepository.save(product);
            } else {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
        } else {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
    }

    // 🔟 Optional: Increase stock (Admin restock)
    public void increaseStock(Integer productId, Double quantity) {
        Product product = getById(productId);
        if (product != null) {
            Double currentStock = product.getStockKg();  // Use stockKg directly
            product.setStockKg(currentStock + quantity);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
    }
}
