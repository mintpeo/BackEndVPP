package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.CategoryItem;
import com.thienlong.vppbackend.service.CategoryItemSer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cate_items")
@CrossOrigin(origins = "${app.frontend.url}")
public class CategoryItemCon {
    private final CategoryItemSer ser;

    public CategoryItemCon(CategoryItemSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<CategoryItem> getAllNameColors() {
        return ser.getAllCateItems();
    }
}
