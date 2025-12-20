package com.example.demo.repository;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository; // Provides built-in functions to save , retrieve , delete the data in a table
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    /*
       Spring boot automatically generates the following SQL query for above declared interface
       SELECT * FROM users WHERE username = ?   
    */

}
