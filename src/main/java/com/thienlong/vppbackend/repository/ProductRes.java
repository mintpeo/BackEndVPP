package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class ProductRes {
    private final WebClient client;

    public ProductRes(WebClient client) {
        this.client = client;
    }

    public List<Product> findAll() {
        return client.get().uri(uriBuilder -> uriBuilder.path("/products")
                        .queryParam("select", "*,images:product_images(*),colors:product_colors(*,info:name_colors(*))").build())
                .retrieve().bodyToFlux(Product.class).collectList().block();
    }
}