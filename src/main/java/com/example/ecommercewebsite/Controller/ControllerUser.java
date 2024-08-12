package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponseUser;
import com.example.ecommercewebsite.Model.User;
import com.example.ecommercewebsite.Service.ServiceUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class ControllerUser {

    private final ServiceUser serviceUser;

    @GetMapping("/get")
    public ResponseEntity getUsers()
    {
        if(serviceUser.getUsers().isEmpty())
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("it is empty"));
        }
        return ResponseEntity.status(200).body(serviceUser.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceUser.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponseUser("User added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid@RequestBody User user, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(serviceUser.updateUser(id, user))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id)
    {
        if(serviceUser.deleteUser(id))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("User deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @PutMapping("/buy-product/{userid}/{merchantstockid}")
    public ResponseEntity buyProduct(@PathVariable Integer userid,@PathVariable Integer merchantstockid)
    {
        if (serviceUser.buyProduct(userid, merchantstockid).equals("true"))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("Operation buy product successfully"));
        }
        if (serviceUser.buyProduct(userid, merchantstockid).equals("id user not found"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("id user not found"));
        }
        if (serviceUser.buyProduct(userid, merchantstockid).equals("id merchantStock not found"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("id merchantStock not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("bad request"));
    }
    @PutMapping("/product-return/{userid}/{merchantstockid}")
    public ResponseEntity productReturn(@PathVariable Integer userid,@PathVariable Integer merchantstockid)
    {
        if (serviceUser.productReturn(userid, merchantstockid).equals("true"))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("Operation return product successfully"));
        }
        if (serviceUser.productReturn(userid, merchantstockid).equals("id user not found"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("id user not found"));
        }
        if (serviceUser.productReturn(userid, merchantstockid).equals("id merchantStock not found"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("id merchantStock not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("Bad request"));
    }
    @PutMapping("/change-price-by-merchant/{newprice}/{mid}/{pid}")
    public ResponseEntity changePriceByMerchant(@PathVariable double newprice,@PathVariable Integer mid,@PathVariable Integer pid)
    {
        if(serviceUser.changePriceByMerchant(newprice, mid, pid).equals("true"))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("The price changed"));
        }
        if(serviceUser.changePriceByMerchant(newprice, mid, pid).equals("merchant id is false"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("merchant id is false"));
        }
        if(serviceUser.changePriceByMerchant(newprice, mid, pid).equals("product id is false"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("product id is false"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @PutMapping("/discount-for-user-who-bought/{userid}")
    public ResponseEntity discountForUserWhoBought(@PathVariable Integer userid)
    {
        if(serviceUser.discountForUserWhoBought(userid).equals("true"))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("Done discount"));
        }
        if(serviceUser.discountForUserWhoBought(userid).equals("id user not found"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("id user not found"));
        }
        if(serviceUser.discountForUserWhoBought(userid).equals("product id not found"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("product id not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("Bad request"));
    }
}
