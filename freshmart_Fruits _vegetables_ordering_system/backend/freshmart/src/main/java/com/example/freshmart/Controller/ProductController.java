


package com.example.freshmart.Controller;

import com.example.freshmart.Entity.Product;
import com.example.freshmart.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/products") // All endpoints start with /api/products
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class ProductController {

    @Autowired
    private ProductServices productServices;

    // 1️⃣ Add product (Admin only) - Multi-unit
    @PostMapping
    public ResponseEntity<Product> addProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "price_per_kg", required = false) Double pricePerKg,
            @RequestParam(value = "stock_kg", required = false) Double stockKg,
            @RequestParam(value = "price_per_g", required = false) Double pricePerGram,
            @RequestParam(value = "stock_g", required = false) Double stockGrams,
            @RequestParam(value = "price_per_piece", required = false) Double pricePerPiece,
            @RequestParam(value = "stock_pcs", required = false) Integer stockPieces,
            @RequestParam("unit") String unit,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setCategory(category);
            product.setDescription(description);
            product.setOrigin(origin);
            product.setPricePerKg(pricePerKg != null ? pricePerKg : 0.0);
            product.setStockKg(stockKg != null ? stockKg : 0.0);
            product.setPricePerGram(pricePerGram != null ? pricePerGram : 0.0);
            product.setStockGrams(stockGrams != null ? stockGrams : 0.0);
            product.setPricePerPiece(pricePerPiece != null ? pricePerPiece : 0.0);
            product.setStockPieces(stockPieces != null ? stockPieces : 0);
            product.setUnit(unit);

            String imageName = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                imageName = imageFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/Images/" + imageName);
                Files.createDirectories(path.getParent());
                Files.write(path, imageFile.getBytes());
            }
            product.setImage(imageName);

            Product savedProduct = productServices.createProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 2️⃣ Get all products
    @GetMapping
    public Collection<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    // 3️⃣ Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Product product = productServices.getById(id);
        if (product == null) return ResponseEntity.status(404).body("Product Not Found");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // 4️⃣ Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        Product product = productServices.getById(id);
        if (product == null) return ResponseEntity.status(404).body("Product Not Found");
        productServices.deleteById(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }

    // 5️⃣ Update product (Admin only) - Multi-unit & image
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "price_per_kg", required = false) Double pricePerKg,
            @RequestParam(value = "stock_kg", required = false) Double stockKg,
            @RequestParam(value = "price_per_g", required = false) Double pricePerGram,
            @RequestParam(value = "stock_g", required = false) Double stockGrams,
            @RequestParam(value = "price_per_piece", required = false) Double pricePerPiece,
            @RequestParam(value = "stock_pcs", required = false) Integer stockPieces,
            @RequestParam("unit") String unit,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            Product existingProduct = productServices.getById(id);
            if (existingProduct == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            existingProduct.setName(name);
            existingProduct.setCategory(category);
            existingProduct.setDescription(description);
            existingProduct.setOrigin(origin);
            existingProduct.setPricePerKg(pricePerKg != null ? pricePerKg : 0.0);
            existingProduct.setStockKg(stockKg != null ? stockKg : 0.0);
            existingProduct.setPricePerGram(pricePerGram != null ? pricePerGram : 0.0);
            existingProduct.setStockGrams(stockGrams != null ? stockGrams : 0.0);
            existingProduct.setPricePerPiece(pricePerPiece != null ? pricePerPiece : 0.0);
            existingProduct.setStockPieces(stockPieces != null ? stockPieces : 0);
            existingProduct.setUnit(unit);

            String imageName = existingProduct.getImage();
            if (imageFile != null && !imageFile.isEmpty()) {
                imageName = imageFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/Images/" + imageName);
                Files.createDirectories(path.getParent());
                Files.write(path, imageFile.getBytes());
            }
            existingProduct.setImage(imageName);

            Product updatedProduct = productServices.updateProduct(id, existingProduct);
            return ResponseEntity.ok(updatedProduct);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 6️⃣ Search products
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productServices.searchByName(keyword);
    }

    // 7️⃣ Filter by category
    @GetMapping("/filter/category")
    public List<Product> filterByCategory(@RequestParam String category) {
        if(category.equalsIgnoreCase("all") || category.trim().isEmpty()) {
            return productServices.getAllProducts().stream().toList();
        }
        return productServices.filterByCategory(category);
    }

    // 8️⃣ Filter by origin/farm
    @GetMapping("/filter/origin")
    public List<Product> filterByOrigin(@RequestParam String origin) {
        return productServices.filterByOrigin(origin);
    }

    // 9️⃣ Reduce stock after order
    @PostMapping("/reduce-stock/{id}")
    public ResponseEntity<?> reduceStock(
            @PathVariable Integer id,
            @RequestParam("quantity") Double quantity // for kg/g
    ) {
        Product product = productServices.getById(id);
        if (product == null) return ResponseEntity.status(404).body("Product not found");

        switch (product.getUnit().toLowerCase()) {
            case "kg":
                double newKg = product.getStockKg() - quantity;
                product.setStockKg(newKg < 0 ? 0.0 : newKg);
                break;
            case "g":
                double newG = product.getStockGrams() - quantity;
                product.setStockGrams(newG < 0 ? 0.0 : newG);
                break;
            case "pcs":
                int newPcs = product.getStockPieces() - quantity.intValue();
                product.setStockPieces(newPcs < 0 ? 0 : newPcs);
                break;
        }

        productServices.updateProduct(id, product);
        return ResponseEntity.ok("Stock updated successfully");
    }
}
