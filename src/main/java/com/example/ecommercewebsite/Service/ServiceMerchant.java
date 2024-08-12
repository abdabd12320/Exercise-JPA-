package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Repository.RepositoryMerchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceMerchant {

    private final RepositoryMerchant repositoryMerchant;

    public List<Merchant> getMerchants() {
        return repositoryMerchant.findAll();
    }

    public void addMerchant(Merchant merchant)
    {
        repositoryMerchant.save(merchant);
    }

    public boolean updateMerchant(Integer id,Merchant merchant)
    {
        Merchant m = repositoryMerchant.getReferenceById(id);
        if(m == null)
        {
            return false;
        }
        m.setName(merchant.getName());
        repositoryMerchant.save(m);
        return true;
    }

    public boolean deleteMerchant(Integer id)
    {
        if(repositoryMerchant.existsById(id))
        {
            repositoryMerchant.deleteById(id);
            return true;
        }
        return false;
    }
}
