package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.Category;
import com.thienlong.vppbackend.service.CategorySer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "${app.frontend.url}")
public class CategoryCon {
    private final CategorySer ser;

    public CategoryCon(CategorySer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return ser.getAllCategories();
    }
}
