package com.example.ecommercewebsite.Repository;

import com.example.ecommercewebsite.Model.MerchantStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMerchantStock extends JpaRepository<MerchantStock,Integer> {
}
