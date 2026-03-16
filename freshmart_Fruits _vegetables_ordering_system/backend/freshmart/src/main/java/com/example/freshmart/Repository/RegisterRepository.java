package com.example.freshmart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer>{

      // This tells Spring Data JPA to find user by email
    Register findByEmail(String email);
    
}
