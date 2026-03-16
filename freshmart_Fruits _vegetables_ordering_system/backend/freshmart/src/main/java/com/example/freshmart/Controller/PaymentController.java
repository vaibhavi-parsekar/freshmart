// package com.example.freshmart.Controller;

// import com.example.freshmart.Entity.Payment;
// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Services.PaymentServices;
// import com.example.freshmart.Services.OrderServices;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// // @RequestMapping("/api/payments")
// @RequestMapping("/payments")
// public class PaymentController {

//     @Autowired
//     private PaymentServices paymentServices;

//     @Autowired
//     private OrderServices orderServices;

//     // Create payment (COD or Fake Online)
//     @PostMapping("/create/{orderId}")
//     public ResponseEntity<?> createPayment(
//             @PathVariable Integer orderId,
//             @RequestParam String method
//     ){
//         Order order = orderServices.getById(orderId);
//         if(order == null) return ResponseEntity.status(404).body("Order not found");

//         Payment payment = paymentServices.createPayment(order, method);
//         return ResponseEntity.ok(payment);
//     }

//     // Update payment status (for Fake Online)
//     @PutMapping("/{paymentId}/status")
//     public ResponseEntity<?> updateStatus(
//             @PathVariable Integer paymentId,
//             @RequestParam String status
//     ){
//         Payment payment = paymentServices.updateStatus(paymentId, status);
//         if(payment == null) return ResponseEntity.status(404).body("Payment not found");
//         return ResponseEntity.ok(payment);
//     }

//     // Get payments for a specific order
//     @GetMapping("/order/{orderId}")
//     public ResponseEntity<?> getPaymentsByOrder(@PathVariable Integer orderId){
//         Order order = orderServices.getById(orderId);
//         if(order == null) return ResponseEntity.status(404).body("Order not found");

//         List<Payment> payments = paymentServices.getPaymentsByOrder(order);
//         return ResponseEntity.ok(payments);
//     }

//     // Get all payments (Admin)
//     @GetMapping("/all")
//     public List<Payment> getAllPayments(){
//         return paymentServices.getAllPayments();
//     }
// }








package com.example.freshmart.Controller;

import com.example.freshmart.Entity.Order;
import com.example.freshmart.Entity.Payment;
import com.example.freshmart.Services.PaymentServices;
import com.example.freshmart.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class PaymentController {

    @Autowired
    private PaymentServices paymentServices;

    @Autowired
    private OrderServices orderServices;

    // -----------------------------
    // Create a payment (COD / Online)
    // -----------------------------
    @PostMapping("/create/{orderId}")
    public ResponseEntity<?> createPayment(
            @PathVariable Integer orderId,
            @RequestBody Map<String, String> payload
    ) {
        Order order = orderServices.getById(orderId);
        if (order == null) return ResponseEntity.status(404).body("Order not found");

        String method = payload.get("method"); // COD or Online
        Payment payment = paymentServices.createPayment(order, method);
        return ResponseEntity.ok(payment);
    }

    // -----------------------------
    // Update payment status (Online)
    // Example: PUT /payments/123/status?status=Success
    // -----------------------------
    @PutMapping("/{paymentId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Integer paymentId,
            @RequestParam String status
    ) {
        Payment payment = paymentServices.updateStatus(paymentId, status);
        if (payment == null) return ResponseEntity.status(404).body("Payment not found");
        return ResponseEntity.ok(payment);
    }

    // -----------------------------
    // Get payments by order
    // -----------------------------
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getPaymentsByOrder(@PathVariable Integer orderId) {
        Order order = orderServices.getById(orderId);
        if (order == null) return ResponseEntity.status(404).body("Order not found");

        List<Payment> payments = paymentServices.getPaymentsByOrder(order);
        return ResponseEntity.ok(payments);
    }

    // -----------------------------
    // Get all payments (Admin)
    // -----------------------------
    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentServices.getAllPayments();
    }
}
