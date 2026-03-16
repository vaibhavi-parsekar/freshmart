// package com.example.freshmart.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Services.UserServices;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// public class UserController {

//     @Autowired
//     private UserServices userServices;

//     // ---------------- Register ----------------
//     @PostMapping("/registers")
//     public ResponseEntity<Register> addUser(@RequestBody Register register) {
//         Register newUser = userServices.addUser(register);
//         return ResponseEntity.ok(newUser);
//     }

//     // ---------------- Login ----------------
//     @PostMapping("/logins")
//     public ResponseEntity<Register> userLogin(@RequestBody com.example.freshmart.Entity.Login login) {
//         Register user = userServices.userLogin(login); // Call your service to check email and password
//         return ResponseEntity.ok(user);
//     }
// }





// /////////// proper working code

// package com.example.freshmart.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Services.UserServices;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

// // 👇 Added imports
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// public class UserController {

//     @Autowired
//     private UserServices userServices;

//     // ---------------- Register ----------------
//     @PostMapping("/registers")
//     public ResponseEntity<Register> addUser(@RequestBody Register register) {
//         Register newUser = userServices.addUser(register);
//         return ResponseEntity.ok(newUser);
//     }

//     // ---------------- Login ----------------
//     @PostMapping("/logins")
//     public ResponseEntity<Register> userLogin(@RequestBody com.example.freshmart.Entity.Login login) {
//         Register user = userServices.userLogin(login);
//         return ResponseEntity.ok(user);
//     }

//     // ================== ADDED FOR PROFILE ==================

//     // -------- Get Profile --------
//     @GetMapping("/profile/{id}")
//     public ResponseEntity<Register> getProfile(@PathVariable Integer id) {
//         Register user = userServices.getUserById(id);
//         return ResponseEntity.ok(user);
//     }

//     // -------- Update Profile --------
//     @PutMapping("/profile/{id}")
//     public ResponseEntity<Register> updateProfile(
//             @PathVariable Integer id,
//             @RequestBody Register updatedUser) {

//         Register user = userServices.updateUser(id, updatedUser);
//         return ResponseEntity.ok(user);
//     }
// }







// WORKING CODE (UPDATED REGISTRATION LOGIC ONLY)

package com.example.freshmart.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.example.freshmart.Entity.Register;
import com.example.freshmart.Services.UserServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    // ---------------- Register ----------------
    @PostMapping("/registers")
    public ResponseEntity<?> addUser(
            @Valid @RequestBody Register register,
            BindingResult result) {

        //  Validation Error Check
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                                        .stream()
                                        .map(error -> error.getDefaultMessage())
                                        .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Register newUser = userServices.addUser(register);

        //  If user not saved (duplicate email or invalid case)
        if (newUser == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists! or invalid data!");
        }

        return ResponseEntity.ok("Registration Successful!");
    }

    // ---------------- Login ----------------
    @PostMapping("/logins")
    public ResponseEntity<?> userLogin(@RequestBody com.example.freshmart.Entity.Login login) {
        Register user = userServices.userLogin(login);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Login failed! Invalid email or password.");
        }

        return ResponseEntity.ok(user);
    }

    // ================== ADDED FOR PROFILE ==================

    // -------- Get Profile --------
    @GetMapping("/profile/{id}")
    public ResponseEntity<Register> getProfile(@PathVariable Integer id) {
        Register user = userServices.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // -------- Update Profile --------
    @PutMapping("/profile/{id}")
    public ResponseEntity<Register> updateProfile(
            @PathVariable Integer id,
            @RequestBody Register updatedUser) {

        Register user = userServices.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }
}








