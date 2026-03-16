package com.example.freshmart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.freshmart.Entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{
    
}
