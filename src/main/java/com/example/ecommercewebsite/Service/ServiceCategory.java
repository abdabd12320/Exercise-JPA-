package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Repository.RepositoryCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCategory {

    private final RepositoryCategory repositoryCategory;

    public List<Category> getCategories() {
        return repositoryCategory.findAll();
    }

    public void addCategory(Category category)
    {
        repositoryCategory.save(category);
    }

    public boolean updateCategory(Integer id,Category category)
    {
        Category c = repositoryCategory.getReferenceById(id);
        if(c == null)
        {
            return false;
        }
        c.setName(category.getName());
        repositoryCategory.save(c);
        return true;
    }

    public boolean deleteCategory(Integer id)
    {
        if(repositoryCategory.existsById(id))
        {
            repositoryCategory.deleteById(id);
            return true;
        }
        return false;
    }
}