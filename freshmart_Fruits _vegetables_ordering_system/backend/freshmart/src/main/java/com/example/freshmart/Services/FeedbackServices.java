// package com.example.freshmart.Services;

// import com.example.freshmart.Entity.Feedback;
// import com.example.freshmart.Entity.Product;
// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Repository.FeedbackRepository;
// import com.example.freshmart.Repository.ProductRepository;
// import com.example.freshmart.Repository.RegisterRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class FeedbackServices {

//     @Autowired
//     private FeedbackRepository feedbackRepository;

//     @Autowired
//     private RegisterRepository registerRepository;

//     @Autowired
//     private ProductRepository productRepository;

//     // Add feedback
//     public Feedback addFeedback(Integer userId, Integer productId, String comment, Integer rating) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         Product product = productRepository.findById(productId).orElse(null);

//         if(user == null || product == null) return null;

//         Feedback feedback = new Feedback();
//         feedback.setUser(user);
//         feedback.setProduct(product);
//         feedback.setComment(comment);
//         feedback.setRating(rating);
//         feedback.setCreatedAt(LocalDateTime.now());

//         return feedbackRepository.save(feedback);
//     }

//     // Get all feedbacks of a user
//     public List<Feedback> getUserFeedbacks(Integer userId) {
//         Register user = registerRepository.findById(userId).orElse(null);
//         if(user == null) return null;
//         return feedbackRepository.findByUser(user);
//     }

//     // Get all feedbacks of a product
//     public List<Feedback> getProductFeedbacks(Integer productId) {
//         Product product = productRepository.findById(productId).orElse(null);
//         if(product == null) return null;
//         return feedbackRepository.findByProduct(product);
//     }

//     // Delete feedback
//     public boolean deleteFeedback(Integer feedbackId) {
//         if(feedbackRepository.existsById(feedbackId)) {
//             feedbackRepository.deleteById(feedbackId);
//             return true;
//         }
//         return false;
//     }
// }









package com.example.freshmart.Services;

import com.example.freshmart.Entity.Feedback;
import com.example.freshmart.Entity.Product;
import com.example.freshmart.Entity.Register;
import com.example.freshmart.Repository.FeedbackRepository;
import com.example.freshmart.Repository.ProductRepository;
import com.example.freshmart.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServices {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ProductRepository productRepository;

    // ------------------ Add feedback ------------------
    public Feedback addFeedback(Integer userId, Integer productId, String comment, Integer rating) {
        Register user = registerRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if(user == null || product == null) return null;

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setProduct(product);
        feedback.setComment(comment);
        feedback.setRating(rating);
        feedback.setCreatedAt(LocalDateTime.now());

        return feedbackRepository.save(feedback);
    }

    // ------------------ Get all feedbacks of a user ------------------
    public List<Feedback> getUserFeedbacks(Integer userId) {
        Register user = registerRepository.findById(userId).orElse(null);
        if(user == null) return null;
        return feedbackRepository.findByUser(user);
    }

    // ------------------ Get all feedbacks of a product ------------------
    public List<Feedback> getProductFeedbacks(Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) return null;
        return feedbackRepository.findByProduct(product);
    }

    // ------------------ Delete feedback ------------------
    public boolean deleteFeedback(Integer feedbackId) {
        if(feedbackRepository.existsById(feedbackId)) {
            feedbackRepository.deleteById(feedbackId);
            return true;
        }
        return false;
    }

    // ------------------ Admin: Get all feedbacks ------------------
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
