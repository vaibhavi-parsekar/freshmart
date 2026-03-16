// package com.example.freshmart.Services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.freshmart.Repository.RegisterRepository;
// import com.example.freshmart.Repository.ProductRepository;
// import com.example.freshmart.Repository.OrderRepository;
// import com.example.freshmart.Repository.PaymentRepository;
// import com.example.freshmart.Repository.FeedbackRepository;

// import java.util.HashMap;
// import java.util.Map;

// @Service
// public class AdminService {

//     @Autowired
//     private RegisterRepository registerRepository;  //  Users

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private PaymentRepository paymentRepository;

//     @Autowired
//     private FeedbackRepository feedbackRepository;

//     public Map<String, Object> getDashboardSummary() {
//         Map<String, Object> summary = new HashMap<>();

//         // Use RegisterRepository instead of UserRepository
//         summary.put("totalUsers", registerRepository.count());
//         summary.put("totalProducts", productRepository.count());
//         summary.put("totalOrders", orderRepository.count());
//         summary.put("totalPayments", paymentRepository.count());
//         summary.put("totalFeedback", feedbackRepository.count());

//         // Optional: fetch last 5 orders
//         summary.put("recentOrders", orderRepository.findTop5ByOrderByIdDesc());

//         return summary;
//     }
// }








// package com.example.freshmart.Services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Repository.RegisterRepository;
// import com.example.freshmart.Repository.ProductRepository;
// import com.example.freshmart.Repository.OrderRepository;
// import com.example.freshmart.Repository.PaymentRepository;
// import com.example.freshmart.Repository.FeedbackRepository;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// @Service
// public class AdminService {

//     @Autowired
//     private RegisterRepository registerRepository;

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private PaymentRepository paymentRepository;

//     @Autowired
//     private FeedbackRepository feedbackRepository;

//     // ---------------- Dashboard Summary ----------------
//     public Map<String, Object> getDashboardSummary() {
//         Map<String, Object> summary = new HashMap<>();

//         summary.put("totalUsers", registerRepository.count());
//         summary.put("totalProducts", productRepository.count());
//         summary.put("totalOrders", orderRepository.count());
//         summary.put("totalPayments", paymentRepository.count());
//         summary.put("totalFeedback", feedbackRepository.count());

//         summary.put("recentOrders", orderRepository.findTop5ByOrderByIdDesc());

//         return summary;
//     }

//     // ---------------- Manage Users ----------------

//     // Get all users
//     public List<Register> getAllUsers() {
//         return registerRepository.findAll();
//     }

//     // Get user by ID
//     public Register getUserById(Integer id) {
//         Optional<Register> user = registerRepository.findById(id);
//         return user.orElse(null);  // or throw exception if you prefer
//     }

//     // Update user
//     public Register updateUser(Integer id, Register updatedUser) {
//         return registerRepository.findById(id).map(user -> {
//             //user.setName(updatedUser.getName());
//             user.setEmail(updatedUser.getEmail());
//             // user.setPhone(updatedUser.getPhone());
//             user.setRole(updatedUser.getRole()); // if you have roles
//             return registerRepository.save(user);
//         }).orElse(null);
//     }

//     // Delete user
//     public void deleteUser(Integer id) {
//         registerRepository.deleteById(id);
//     }
// }




package com.example.freshmart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freshmart.Entity.Register;
import com.example.freshmart.Repository.RegisterRepository;
import com.example.freshmart.Repository.ProductRepository;
import com.example.freshmart.Repository.OrderRepository;
import com.example.freshmart.Repository.PaymentRepository;
import com.example.freshmart.Repository.FeedbackRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ---------------- Dashboard Summary ----------------
    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();

        summary.put("totalUsers", registerRepository.count());
        summary.put("totalProducts", productRepository.count());
        summary.put("totalOrders", orderRepository.count());
        summary.put("totalPayments", paymentRepository.count());
        summary.put("totalFeedback", feedbackRepository.count());

        summary.put("recentOrders", orderRepository.findTop5ByOrderByIdDesc());

        return summary;
    }

    // ---------------- Manage Users ----------------

    // Get all users
    public List<Register> getAllUsers() {
        return registerRepository.findAll();
    }

    // Get user by ID
    public Register getUserById(Integer id) {
        Optional<Register> user = registerRepository.findById(id);
        return user.orElse(null);  // or throw exception if you prefer
    }

    // Update user
    public Register updateUser(Integer id, Register updatedUser) {
        return registerRepository.findById(id).map(user -> {
            if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty())
                user.setUsername(updatedUser.getUsername());   // Update Name if not null
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty())
                user.setEmail(updatedUser.getEmail());         // Update Email if not null
            if (updatedUser.getMobileno() != null && !updatedUser.getMobileno().isEmpty())
                user.setMobileno(updatedUser.getMobileno());   // Update Phone if not null
            if (updatedUser.getRole() != null && !updatedUser.getRole().isEmpty())
                user.setRole(updatedUser.getRole());           // Update Role if not null
            if (updatedUser.getAddress() != null && !updatedUser.getAddress().isEmpty())
                user.setAddress(updatedUser.getAddress());     // Update Address if not null
            return registerRepository.save(user);              // Save changes to DB
        }).orElse(null);
    }

    // Delete user
    public void deleteUser(Integer id) {
        registerRepository.deleteById(id);
    }
}
