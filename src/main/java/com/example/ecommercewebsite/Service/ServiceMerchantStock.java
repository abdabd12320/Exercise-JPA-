package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Repository.RepositoryMerchant;
import com.example.ecommercewebsite.Repository.RepositoryMerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceMerchantStock {

    private final RepositoryMerchantStock repositoryMerchantStock;

    public List<MerchantStock> getMerchantStocks() {
        return repositoryMerchantStock.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock)
    {
        repositoryMerchantStock.save(merchantStock);
    }

    public boolean updateMerchantStock(Integer id,MerchantStock merchantStock)
    {
        MerchantStock m = repositoryMerchantStock.getReferenceById(id);
        if(m == null)
        {
            return false;
        }
        m.setProductID(merchantStock.getProductID());
        m.setMerchantID(merchantStock.getMerchantID());
        m.setStock(merchantStock.getStock());
        repositoryMerchantStock.save(m);
        return true;

    }

    public boolean deleteMerchantStock(Integer id)
    {
        if(repositoryMerchantStock.existsById(id))
        {
            repositoryMerchantStock.delete(repositoryMerchantStock.getReferenceById(id));
            return true;
        }
        return false;
    }

    public boolean changeStock(int stock, char c,Integer id) {
        MerchantStock m = repositoryMerchantStock.getReferenceById(id);
        if (m == null) {
            return false;
        }
        if (c == 'r' || c == 'R') {
            m.setStock(m.getStock() + stock);
            repositoryMerchantStock.save(m);
            return true;
        }
        if (c == 'l' || c == 'L') {
            m.setStock(m.getStock() - stock);
            repositoryMerchantStock.save(m);
            return true;
        }
        return false;
    }
}
