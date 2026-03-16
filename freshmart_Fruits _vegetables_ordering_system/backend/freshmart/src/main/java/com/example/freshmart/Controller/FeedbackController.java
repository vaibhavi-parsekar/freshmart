

// package com.example.freshmart.Controller;

// import com.example.freshmart.Entity.Feedback;
// import com.example.freshmart.Services.FeedbackServices;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/feedback")
// @CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
// public class FeedbackController {

//     @Autowired
//     private FeedbackServices feedbackServices;

//     // Add feedback
//     @PostMapping("/add")
//     public ResponseEntity<?> addFeedback(
//             @RequestParam Integer userId,
//             @RequestParam Integer productId,
//             @RequestParam String comment,
//             @RequestParam Integer rating
//     ){
//         Feedback feedback = feedbackServices.addFeedback(userId, productId, comment, rating);
//         if(feedback == null) return ResponseEntity.status(400).body("Invalid userId or productId");
//         return ResponseEntity.ok(feedback);
//     }

//     // Get feedbacks of a user
//     @GetMapping("/user/{userId}")
//     public ResponseEntity<List<Feedback>> getUserFeedbacks(@PathVariable Integer userId){
//         List<Feedback> feedbacks = feedbackServices.getUserFeedbacks(userId);
//         return ResponseEntity.ok(feedbacks);
//     }

//     // Get feedbacks of a product
//     @GetMapping("/product/{productId}")
//     public ResponseEntity<List<Feedback>> getProductFeedbacks(@PathVariable Integer productId){
//         List<Feedback> feedbacks = feedbackServices.getProductFeedbacks(productId);
//         return ResponseEntity.ok(feedbacks);
//     }

//     // Delete feedback
//     @DeleteMapping("/delete/{feedbackId}")
//     public ResponseEntity<?> deleteFeedback(@PathVariable Integer feedbackId){
//         boolean deleted = feedbackServices.deleteFeedback(feedbackId);
//         if(!deleted) return ResponseEntity.status(404).body("Feedback not found");
//         return ResponseEntity.ok("Feedback deleted successfully");
//     }
// }









package com.example.freshmart.Controller;

import com.example.freshmart.Entity.Feedback;
import com.example.freshmart.Services.FeedbackServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:3000") //  Allow React frontend
public class FeedbackController {

    @Autowired
    private FeedbackServices feedbackServices;

    // Add feedback
    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(
            @RequestParam Integer userId,
            @RequestParam Integer productId,
            @RequestParam String comment,
            @RequestParam Integer rating
    ){
        Feedback feedback = feedbackServices.addFeedback(userId, productId, comment, rating);
        if(feedback == null) return ResponseEntity.status(400).body("Invalid userId or productId");
        return ResponseEntity.ok(feedback);
    }

    // Get feedbacks of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getUserFeedbacks(@PathVariable Integer userId){
        List<Feedback> feedbacks = feedbackServices.getUserFeedbacks(userId);
        return ResponseEntity.ok(feedbacks);
    }

    // Get feedbacks of a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Feedback>> getProductFeedbacks(@PathVariable Integer productId){
        List<Feedback> feedbacks = feedbackServices.getProductFeedbacks(productId);
        return ResponseEntity.ok(feedbacks);
    }

    // Delete feedback
    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Integer feedbackId){
        boolean deleted = feedbackServices.deleteFeedback(feedbackId);
        if(!deleted) return ResponseEntity.status(404).body("Feedback not found");
        return ResponseEntity.ok("Feedback deleted successfully");
    }

    // ------------------ Admin: Get all feedbacks ------------------
    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackServices.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }
}
