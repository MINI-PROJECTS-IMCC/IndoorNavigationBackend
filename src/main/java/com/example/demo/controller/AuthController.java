// Handles login and register requests.Used for user authentication only.
package com.example.demo.controller;

import com.example.demo.model.User; // We have created this package and import it here
import com.example.demo.repository.UserRepository; // We have created this package and import it here
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Register new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Checks if the username already exists or not
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        /*
            Username Validations :-
            - Must starts with alphabet only
            - length can be 3-10 characters only
            - Can contains alphabets , digits  and underscore only
        */
       // The following if block Checks for the following pattern in username
        String username = user.getUsername();
        if (!username.matches("^[A-Za-z][A-Za-z0-9_]{2,9}$")) {
            return "Invalid username! Username must start with a letter, be 3-10 characters long, and contain only letters, digits, and underscores.";
        }
        // Validate the password
        /*
            Password Validataion :-
            - Length can be 3-10 characters only 
            - Should contain atleast one alphabet , one digit , one special symbol
        */
        String password = user.getPassword();
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{3,10}$")) {
        return "Invalid password! Password must be 3-10 characters long, and include at least one letter, one number, and one special character.";
        }
        // If everything is perfect then save the user
        userRepository.save(user);
        return "User registered successfully!";
    }
    // Login
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    }
}
