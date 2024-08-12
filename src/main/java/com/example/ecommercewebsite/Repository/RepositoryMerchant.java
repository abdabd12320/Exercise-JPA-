package com.example.ecommercewebsite.Repository;

import com.example.ecommercewebsite.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMerchant extends JpaRepository<Merchant,Integer> {
}
