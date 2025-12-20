package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id // Means this column is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Name of the column

    @Column(nullable = false, unique = true) // by using @column() we set the constraints on the column
    private String username; // By default the variable names are used as column names but , by using 'name' property you can change the column name for the table

    @Column(nullable = false)
    private String password;

    // Un-parameterised constructor
    public User() {} 

    // Parameterised constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // return the id 
    public Long getId() {
        return id;
    }

    // returns the user name
    public String getUsername() {
        return username;
    }

    // sets the username value
    public void setUsername(String username) {
        this.username = username;
    }

    // returns the password
    public String getPassword() {
        return password;
    }

    // sets the password
    public void setPassword(String password) {
        this.password = password;
    }

}
