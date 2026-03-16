

package com.example.freshmart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.freshmart.Entity.Register;
import com.example.freshmart.Entity.Order;
import com.example.freshmart.Services.AdminService;
import com.example.freshmart.Services.OrderServices;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderServices orderService;  // New service for orders

    // ---------------- Dashboard Summary ----------------
    @GetMapping("/dashboard-summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        Map<String, Object> summary = adminService.getDashboardSummary();
        return ResponseEntity.ok(summary);
    }

    // ---------------- Manage Users ----------------

    @GetMapping("/users")
    public ResponseEntity<List<Register>> getAllUsers() {
        List<Register> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Register> getUserById(@PathVariable Integer id) {
        Register user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Register> updateUser(@PathVariable Integer id, @RequestBody Register updatedUser) {
        Register user = adminService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Recent Orders ----------------
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders(); // fetch all orders with user + orderItems
        return ResponseEntity.ok(orders);
    }
}
