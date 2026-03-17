package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.CategoryItem;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class CategoryItemRes {
    private final WebClient client;

    public CategoryItemRes(WebClient client) {
        this.client = client;
    }

    public List<CategoryItem> findAll() {
        return client.get().uri("/category_items").retrieve().bodyToFlux(CategoryItem.class).collectList().block();
    }
}