package com.example.ecommercewebsite.Repository;

import com.example.ecommercewebsite.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<User,Integer> {
}
