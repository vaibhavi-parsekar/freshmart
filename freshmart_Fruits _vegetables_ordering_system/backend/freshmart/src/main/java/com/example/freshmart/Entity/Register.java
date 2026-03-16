// package com.example.freshmart.Entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.Data;

// @Data
// @Entity(name = "users") 
// public class Register {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;
//     private String username;
//     private String email;
//     private String password;
//     private String confirmpassword;
//     private String address;
//     private String mobileno;
    

//      private String role;
// }









package com.example.freshmart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "users") 
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 to 20 characters")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", 
         message = "Username should contain only letters and single spaces between words")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "enter a valid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
     @Size(min = 6,  max = 10, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirmpassword  is required")
    private String confirmpassword;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Mobile No is required")
    @Pattern(regexp="^[0-9]{10}$", message="mobile number must be 10 digits")
    private String mobileno;
    

     private String role;
}



