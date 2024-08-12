package com.example.ecommercewebsite.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "productID should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer productID;
    @NotNull(message = "MerchantID should not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer merchantID;
    @NotNull(message = "Stock should not be empty")
    @Min(value = 11,message = "Stock should be more than 10 numbers")
    @Column(columnDefinition = "INT NOT NULL")
    private int stock;
}
