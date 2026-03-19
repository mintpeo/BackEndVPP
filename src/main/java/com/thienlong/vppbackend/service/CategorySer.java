package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.entity.Category;
import com.thienlong.vppbackend.repository.CategoryRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySer {
    private final CategoryRep rep;

    public CategorySer(CategoryRep rep) {
        this.rep = rep;
    }

    public List<Category> getAllCategories() {
        return rep.findAll();
    }
}
