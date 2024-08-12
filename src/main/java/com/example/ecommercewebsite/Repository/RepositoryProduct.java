package com.example.ecommercewebsite.Repository;

import com.example.ecommercewebsite.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProduct extends JpaRepository<Product,Integer> {
}
