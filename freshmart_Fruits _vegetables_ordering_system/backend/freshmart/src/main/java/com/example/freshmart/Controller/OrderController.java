// package com.example.freshmart.Controller;

// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Services.OrderServices;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {

//     @Autowired
//     private OrderServices orderServices;

//     // 1️ Place order from cart
//     @PostMapping("/place")
//     public ResponseEntity<?> placeOrder(@RequestParam Integer userId,
//                                         @RequestParam String paymentMethod) {
//         Order order = orderServices.placeOrder(userId, paymentMethod);
//         if(order == null)
//             return ResponseEntity.badRequest().body("Order could not be placed. Cart empty or invalid user.");
//         return ResponseEntity.ok(order);
//     }

//     // 2️ Get all orders for a user
//     @GetMapping("/user/{userId}")
//     public ResponseEntity<?> getUserOrders(@PathVariable Integer userId) {
//         List<Order> orders = orderServices.getUserOrders(userId);
//         return ResponseEntity.ok(orders);
//     }

//     // 3️ Get all orders (Admin)
//     @GetMapping("/all")
//     public ResponseEntity<?> getAllOrders() {
//         List<Order> orders = orderServices.getAllOrders();
//         return ResponseEntity.ok(orders);
//     }

//     // 4️ Update order status (Admin)
//     @PutMapping("/{orderId}/status")
//     public ResponseEntity<?> updateOrderStatus(@PathVariable Integer orderId,
//                                                @RequestParam String status) {
//         Order updated = orderServices.updateOrderStatus(orderId, status);
//         if(updated == null)
//             return ResponseEntity.badRequest().body("Order not found");
//         return ResponseEntity.ok(updated);
//     }
// }







package com.example.freshmart.Controller;

import com.example.freshmart.Entity.Order;
import com.example.freshmart.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
//  Allow React frontend requests (CORS)
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    // 1️ Place order from cart
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestParam Integer userId,
                                        @RequestParam String paymentMethod) {

        Order order = orderServices.placeOrder(userId, paymentMethod);

        if(order == null) {
            return ResponseEntity.badRequest()
                    .body("Order could not be placed. Cart empty or invalid user.");
        }

        return ResponseEntity.ok(order);
    }

    // 2️ Get all orders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserOrders(@PathVariable Integer userId) {
        List<Order> orders = orderServices.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }

    // 3️ Get all orders (Admin)
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderServices.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // 4️ Update order status (Admin or Payment update)
    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Integer orderId,
                                               @RequestParam String status) {

        Order updated = orderServices.updateOrderStatus(orderId, status);

        if(updated == null) {
            return ResponseEntity.badRequest().body("Order not found");
        }

        return ResponseEntity.ok(updated);
    }

    // 5️ Get a single order by ID (for Payment Page)
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer orderId) {

        Order order = orderServices.getById(orderId);

        if(order == null) {
            return ResponseEntity.status(404).body("Order not found");
        }

        //  Return order including orderItems (products)
        return ResponseEntity.ok(order);
    }
}
