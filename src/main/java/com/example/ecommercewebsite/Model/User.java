package com.example.ecommercewebsite.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 6,message = "Username should be more than 5 characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{7,40}$",message = "Password must have digits and characters, also should be more than 6 length")
    @Column(columnDefinition = "VARCHAR(40) NOT NULL")
    private String password;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "The email must contain an @ sign")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String email;
    @NotEmpty(message = "Role should not be empty")
    @Pattern(regexp = "Admin|Customer")
    @Column(columnDefinition = "VARCHAR(8) NOT NULL")
    private String role;
    @NotNull(message = "Balance should not be empty")
    @Positive(message = "Balance should be positive")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private double balance;
    @AssertFalse
    @Column(columnDefinition = "BOOLEAN")
    private boolean isBuy;
}
