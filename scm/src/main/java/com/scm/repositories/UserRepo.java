package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;



@Repository
public interface UserRepo extends JpaRepository<User , String> {
    // Extra methods related db related operations
    // Custom query methods
    // Custom finder methods

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String Email, String Password);

    Optional<User> findByEmailToken(String id);
    
} 