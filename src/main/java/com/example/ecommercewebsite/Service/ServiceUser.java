package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.User;
import com.example.ecommercewebsite.Repository.RepositoryMerchant;
import com.example.ecommercewebsite.Repository.RepositoryMerchantStock;
import com.example.ecommercewebsite.Repository.RepositoryProduct;
import com.example.ecommercewebsite.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {

    private final RepositoryUser repositoryUser;
    private final RepositoryMerchantStock repositoryMerchantStock;
    private final RepositoryProduct repositoryProduct;
    private final RepositoryMerchant repositoryMerchant;

    private final ServiceMerchantStock serviceMerchantStock;
    private final ServiceProduct serviceProduct;
    private final ServiceCategory serviceCategory;
    private final ServiceMerchant serviceMerchant;

    public List<User> getUsers() {
        return repositoryUser.findAll();
    }

    public void addUser(User user)
    {
        repositoryUser.save(user);
    }

    public boolean updateUser(Integer id, User user)
    {
        User u = repositoryUser.getReferenceById(id);
        if(u == null)
        {
            return false;
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setBalance(user.getBalance());
        u.setBuy(user.isBuy());
        repositoryUser.save(u);
        return true;
    }

    public boolean deleteUser(Integer id)
    {
        if(repositoryUser.existsById(id))
        {
            repositoryUser.delete(repositoryUser.getReferenceById(id));
            return true;
        }
        return false;
    }

    public String buyProduct(Integer userID,Integer merchantStockID)
    {
        User u = repositoryUser.getReferenceById(userID);
        MerchantStock ms = repositoryMerchantStock.getReferenceById(merchantStockID);
        if(u == null)
        {
            return "id user not found";
        }
        if(ms == null)
        {
            return "id merchantStock not found";
        }

        if(u.getRole().equalsIgnoreCase("Customer")) {
            ms.setStock(ms.getStock() - 1);
            u.setBalance(u.getBalance() - repositoryProduct.getReferenceById(ms.getProductID()).getPrice());
            u.setBuy(true);
            repositoryMerchantStock.save(ms);
            repositoryUser.save(u);
            return "true";
        }
        return "false";
    }

    public String productReturn(Integer userID,Integer merchantStockID)
    {
        User u = repositoryUser.getReferenceById(userID);
        MerchantStock ms = repositoryMerchantStock.getReferenceById(merchantStockID);
        if(u == null)
        {

            return "id user not found";
        }
        if(ms == null)
        {
            return "id merchantStock not found";
        }

        if(u.getRole().equalsIgnoreCase("Customer")) {
            ms.setStock(ms.getStock() + 1);
            u.setBalance(u.getBalance() + repositoryProduct.getReferenceById(ms.getProductID()).getPrice());
            u.setBuy(false);
            repositoryMerchantStock.save(ms);
            repositoryUser.save(u);
            return "true";
        }
        return "false";
    }

    public String discountForUserWhoBought(Integer userID)//discount 10%
    {
        User u = repositoryUser.getReferenceById(userID);
        if(u == null)
        {
            return "id user not found";
        }

        if(u.isBuy() && u.getRole().equalsIgnoreCase("Customer"))
        {
            for (int i = 1; i <= repositoryProduct.count(); i++) {
                Product p = repositoryProduct.getReferenceById(i);
                if(p == null)
                {
                    return "product id not found";
                }
                p.setPrice(p.getPrice() - (p.getPrice() * 10 / 100));
                repositoryProduct.save(p);
            }
        }
        return "true";
    }

    public String changePriceByMerchant(double newprice, Integer mID, Integer pID)
    {
        Merchant m = repositoryMerchant.getReferenceById(mID);
        Product p = repositoryProduct.getReferenceById(pID);

        if(m == null)
        {
            return "merchant id is false";
        }
        if(p == null)
        {
            return "product id is false";
        }
        p.setPrice(newprice);
        repositoryProduct.save(p);
        return "true";
    }
}