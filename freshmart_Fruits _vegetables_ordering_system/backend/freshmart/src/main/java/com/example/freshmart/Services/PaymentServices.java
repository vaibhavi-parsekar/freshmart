// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Payment;
// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Repository.PaymentRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class PaymentServices {

//     @Autowired
//     private PaymentRepository paymentRepository;

//     // Create payment for an order (COD / Fake Online)
//     public Payment createPayment(Order order, String method) {
//         Payment payment = new Payment();
//         payment.setOrder(order);
//         payment.setPaymentMethod(method);
//         payment.setAmount(order.getTotalAmount());
//         payment.setStatus(method.equals("COD") ? "Success" : "Pending"); // COD auto-success
//         payment.setPaymentDate(LocalDateTime.now());

//         return paymentRepository.save(payment);
//     }

//     // Update payment status (for Fake Online)
//     public Payment updateStatus(Integer paymentId, String status) {
//         Payment payment = paymentRepository.findById(paymentId).orElse(null);
//         if(payment != null){
//             payment.setStatus(status);
//             payment.setPaymentDate(LocalDateTime.now());
//             return paymentRepository.save(payment);
//         }
//         return null;
//     }

//     // Get payments by order
//     public List<Payment> getPaymentsByOrder(Order order){
//         return paymentRepository.findByOrder(order);
//     }

//     // Get all payments (Admin)
//     public List<Payment> getAllPayments(){
//         return paymentRepository.findAll();
//     }
// }








// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.Payment;
// import com.example.freshmart.Repository.PaymentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class PaymentServices {

//     @Autowired
//     private PaymentRepository paymentRepository;

//     // -------------------------
//     // Create a payment for an order
//     // COD → Success
//     // Online → Pending
//     // -------------------------
//     public Payment createPayment(Order order, String method) {
//         Payment payment = new Payment();
//         payment.setOrder(order);
//         payment.setPaymentMethod(method);
//         payment.setAmount(order.getTotalAmount());
//         payment.setStatus(method.equalsIgnoreCase("COD") ? "Success" : "Pending");
//         payment.setPaymentDate(LocalDateTime.now());

//         return paymentRepository.save(payment);
//     }

//     // -------------------------
//     // Update payment status (Online payment)
//     // status = "Success" or "Failed"
//     // -------------------------
//     public Payment updateStatus(Integer paymentId, String status) {
//         Payment payment = paymentRepository.findById(paymentId).orElse(null);
//         if (payment != null) {
//             payment.setStatus(status);
//             payment.setPaymentDate(LocalDateTime.now());
//             return paymentRepository.save(payment);
//         }
//         return null;
//     }

//     // -------------------------
//     // Get all payments for a specific order
//     // -------------------------
//     public List<Payment> getPaymentsByOrder(Order order) {
//         return paymentRepository.findByOrder(order);
//     }

//     // -------------------------
//     // Get all payments for a user (via orders)
//     // -------------------------
//     public List<Payment> getPaymentsByUser(Integer userId) {
//         return paymentRepository.findByOrder_UserId(userId);
//     }

//     // -------------------------
//     // Get all payments (Admin view)
//     // -------------------------
//     public List<Payment> getAllPayments() {
//         return paymentRepository.findAll();
//     }
// }










// WORKING CODE HAI


// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.Payment;
// import com.example.freshmart.Repository.PaymentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class PaymentServices {

//     @Autowired
//     private PaymentRepository paymentRepository;

//     // -------------------------
//     // Create a payment for an order
//     // COD → Success
//     // Online → Pending
//     // -------------------------
//     public Payment createPayment(Order order, String method) {
//         Payment payment = new Payment();
//         payment.setOrder(order);
//         payment.setPaymentMethod(method);
//         payment.setAmount(order.getTotalAmount());

//         payment.setStatus(method.equalsIgnoreCase("COD") ? "Pending" : "Success");
        
        

//         payment.setPaymentDate(LocalDateTime.now());

//         return paymentRepository.save(payment);
//     }

//     // -------------------------
//     // Update payment status (Online payment)
//     // status = "Success" or "Failed"
//     // -------------------------
//     public Payment updateStatus(Integer paymentId, String status) {
//         Payment payment = paymentRepository.findById(paymentId).orElse(null);
//         if (payment != null) {
//             payment.setStatus(status);
//             payment.setPaymentDate(LocalDateTime.now());
//             return paymentRepository.save(payment);
//         }
//         return null;
//     }

//     // -------------------------
//     // Get all payments for a specific order
//     // -------------------------
//     public List<Payment> getPaymentsByOrder(Order order) {
//         return paymentRepository.findByOrder(order);
//     }

//     // -------------------------
//     // Get all payments for a user (via orders)
//     // -------------------------
//     public List<Payment> getPaymentsByUser(Integer userId) {
//         return paymentRepository.findByOrder_UserId(userId);
//     }

//     // -------------------------
//     // Get all payments (Admin view)
//     // -------------------------
//     public List<Payment> getAllPayments() {
//         return paymentRepository.findAll();
//     }
// }











// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Order;
// import com.example.freshmart.Entity.Payment;
// import com.example.freshmart.Repository.PaymentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class PaymentServices {

//     @Autowired
//     private PaymentRepository paymentRepository;

//     // -------------------------
//     // Create a payment for an order
//     // COD → Success
//     // Online → Pending
//     // -------------------------
//     public Payment createPayment(Order order, String method) {
//         Payment payment = new Payment();
//         payment.setOrder(order);
//         payment.setPaymentMethod(method);
//         payment.setAmount(order.getTotalAmount());

//         // ✅ Corrected logic
//         payment.setStatus(method.equalsIgnoreCase("COD") ? "Success" : "Pending");

//         payment.setPaymentDate(LocalDateTime.now());

//         // ✅ If COD → directly confirm order
//         if (method.equalsIgnoreCase("COD")) {
//             order.setStatus("Confirmed");
//         }

//         return paymentRepository.save(payment);
//     }

//     // -------------------------
//     // Update payment status (Online payment)
//     // status = "Success" or "Failed"
//     // -------------------------
//     public Payment updateStatus(Integer paymentId, String status) {
//         Payment payment = paymentRepository.findById(paymentId).orElse(null);

//         if (payment != null) {
//             payment.setStatus(status);
//             payment.setPaymentDate(LocalDateTime.now());

//             // ✅ If payment successful → confirm order
//             if (status.equalsIgnoreCase("Success")) {
//                 Order order = payment.getOrder();
//                 order.setStatus("Confirmed");
//             }

//             return paymentRepository.save(payment);
//         }

//         return null;
//     }

//     // -------------------------
//     // Get all payments for a specific order
//     // -------------------------
//     public List<Payment> getPaymentsByOrder(Order order) {
//         return paymentRepository.findByOrder(order);
//     }

//     // -------------------------
//     // Get all payments for a user (via orders)
//     // -------------------------
//     public List<Payment> getPaymentsByUser(Integer userId) {
//         return paymentRepository.findByOrder_UserId(userId);
//     }

//     // -------------------------
//     // Get all payments (Admin view)
//     // -------------------------
//     public List<Payment> getAllPayments() {
//         return paymentRepository.findAll();
//     }
// }








package com.example.freshmart.Services;

import com.example.freshmart.Entity.Order;
import com.example.freshmart.Entity.Payment;
import com.example.freshmart.Entity.Product;
import com.example.freshmart.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServices {

    @Autowired
    private PaymentRepository paymentRepository;

    // -------------------------
    // Create a payment for an order
    // COD → Success
    // Online → Pending
    // -------------------------
    public Payment createPayment(Order order, String method) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(method);
        payment.setAmount(order.getTotalAmount());

        payment.setStatus(method.equalsIgnoreCase("COD") ? "Success" : "Pending");
        payment.setPaymentDate(LocalDateTime.now());

        if (method.equalsIgnoreCase("COD")) {
            order.setStatus("Confirmed");
        }

        return paymentRepository.save(payment);
    }

  
    public Payment updateStatus(Integer paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);

        if (payment != null) {
            payment.setStatus(status);
            payment.setPaymentDate(LocalDateTime.now());

            if (status.equalsIgnoreCase("Success")) {
                Order order = payment.getOrder();
                order.setStatus("Confirmed");

                //  STOCK REDUCTION (Unit Based)
                order.getOrderItems().forEach(item -> {

                    Product product = item.getProduct();
                    Double orderedQty = item.getQuantityKg(); // your OrderItem field

                    if (product.getUnit() == null) return;

                    switch (product.getUnit().toLowerCase()) {

                        case "kg":
                            if (product.getStockKg() != null) {
                                product.setStockKg(product.getStockKg() - orderedQty);
                            }
                            break;

                        case "g":
                            if (product.getStockGrams() != null) {
                                product.setStockGrams(product.getStockGrams() - orderedQty);
                            }
                            break;

                        case "pcs":
                            if (product.getStockPieces() != null) {
                                product.setStockPieces(product.getStockPieces() - orderedQty.intValue());
                            }
                            break;
                    }
                });
            }

            return paymentRepository.save(payment);
        }

        return null;
    }

    // -------------------------
    public List<Payment> getPaymentsByOrder(Order order) {
        return paymentRepository.findByOrder(order);
    }

    public List<Payment> getPaymentsByUser(Integer userId) {
        return paymentRepository.findByOrder_UserId(userId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}