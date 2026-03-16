// package com.example.freshmart.Services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.freshmart.Entity.Login;
// import com.example.freshmart.Entity.Register;
// import com.example.freshmart.Repository.RegisterRepository;

// @Service
// public class UserServices {

//     @Autowired
//     private RegisterRepository registerRepository;

//     // ---------------- Register user ----------------
//     public Register addUser(Register register) {
//         // Optional: Check if email already exists
//         if (registerRepository.findByEmail(register.getEmail()) != null) {
//             return null; // Email already registered
//         }

//         // Optional: Check password and confirm password
//         if (!register.getPassword().equals(register.getConfirmpassword())) {
//             return null; // Passwords do not match
//         }

//         return registerRepository.save(register);
//     }

//     // ---------------- Login user ----------------
//     public Register userLogin(Login login) {
//         Register user = registerRepository.findByEmail(login.getEmail());
//         if (user != null && user.getPassword().equals(login.getPassword())) {
//             return user; // Login success
//         }
//         return null; // Login failed
//     }
// }



//WORKING CODE


package com.example.freshmart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freshmart.Entity.Login;
import com.example.freshmart.Entity.Register;
import com.example.freshmart.Repository.RegisterRepository;

@Service
public class UserServices {

    @Autowired
    private RegisterRepository registerRepository;

    // ---------------- Register user ----------------
    public Register addUser(Register register) {
        // Optional: Check if email already exists
        if (registerRepository.findByEmail(register.getEmail()) != null) {
            return null; // Email already registered
        }

        // Optional: Check password and confirm password
        if (!register.getPassword().equals(register.getConfirmpassword())) {
            return null; // Passwords do not match
        }

        return registerRepository.save(register);
    }

    // ---------------- Login user ----------------
    public Register userLogin(Login login) {
        Register user = registerRepository.findByEmail(login.getEmail());
        if (user != null && user.getPassword().equals(login.getPassword())) {
            return user; // Login success
        }
        return null; // Login failed
    }

    // ================== ADDED FOR PROFILE ==================

    // Get User By ID (Profile View)
    public Register getUserById(Integer id) {
        return registerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update User Profile
    public Register updateUser(Integer id, Register updatedUser) {

        Register user = registerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updatedUser.getUsername());
        user.setAddress(updatedUser.getAddress());
        user.setMobileno(updatedUser.getMobileno());

        return registerRepository.save(user);
    }

}



