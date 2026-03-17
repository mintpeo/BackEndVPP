package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.ProductColor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class ProductColorRes {
    private final WebClient client;

    public ProductColorRes(WebClient client) {
        this.client = client;
    }

    public List<ProductColor> findAll() {
        return client.get().uri(uriBuilder -> uriBuilder.path("/product_colors").
                        queryParam("select", "*,info:name_colors(*)").build())
                .retrieve().bodyToFlux(ProductColor.class).collectList().block();
    }
}