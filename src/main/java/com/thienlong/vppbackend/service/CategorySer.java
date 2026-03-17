package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.Category;
import com.thienlong.vppbackend.repository.CategoryRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySer {
    private final CategoryRes res;

    public CategorySer(CategoryRes res) {
        this.res = res;
    }

    public List<Category> getAllCategories() {
        return res.findAll();
    }
}
