package com.example.ecommercewebsite.Repository;

import com.example.ecommercewebsite.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCategory extends JpaRepository<Category,Integer> {
}
