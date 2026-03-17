package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.CategoryItem;
import com.thienlong.vppbackend.repository.CategoryItemRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryItemSer {
    private final CategoryItemRes res;

    public CategoryItemSer(CategoryItemRes res) {
        this.res = res;
    }

    public List<CategoryItem> getAllCateItems() {
        return res.findAll();
    }
}
