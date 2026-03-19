package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class CategoryRep {
    private final WebClient client;

    public CategoryRep(WebClient client) {
        this.client = client;
    }

    public List<Category> findAll() {
        return client.get().uri(uriBuilder -> uriBuilder.path("/categories").
                        queryParam("select", "*,items:category_items(*)").build())
                .retrieve().bodyToFlux(Category.class).collectList().block();
    }
}