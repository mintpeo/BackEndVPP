package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.ProductImage;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class ProductImageRes {
    private final WebClient client;

    public ProductImageRes(WebClient client) {
        this.client = client;
    }

    public List<ProductImage> findAll() {
        return client.get().uri("/product_images").retrieve().bodyToFlux(ProductImage.class).collectList().block();
    }
}