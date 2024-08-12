package com.example.ecommercewebsite.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4,message = "Name should be more than 3 characters")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    private String name;
    @NotNull(message = "Price should not be empty")
    @Positive(message = "Price should be positive numbers")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private double price;
    @NotNull(message = "CategoryID should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer categoryID;
}
