package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Repository.RepositoryProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceProduct {

    private final RepositoryProduct repositoryProduct;

    public List<Product> getProducts() {
        return repositoryProduct.findAll();
    }

    public void addProduct(Product product)
    {
        repositoryProduct.save(product);
    }

    public boolean updateProduct(Integer id,Product product) {
        Product p = repositoryProduct.getReferenceById(id);
        if (p == null) {
            return false;
        }
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategoryID(product.getCategoryID());
        repositoryProduct.save(p);
        return true;
    }
    public boolean deleteProduct(Integer id)
    {
        if(repositoryProduct.existsById(id))
        {
            repositoryProduct.delete(repositoryProduct.getReferenceById(id));
            return true;
        }
        return false;
    }

    public Product searchByProduct(String name)
    {
        for (Integer i = 1; i <= repositoryProduct.count(); i++) {
            if(repositoryProduct.getReferenceById(i).getName().equalsIgnoreCase(name))
            {
                return repositoryProduct.getReferenceById(i);
            }
        }
        return null;
    }
}
