package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.entity.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class ProductRep {
    private final WebClient client;

    public ProductRep(@Qualifier("guestsClient") WebClient client) {
        this.client = client;
    }

    public List<Product> findAll() {
        return client.get().uri(uriBuilder -> uriBuilder.path("/products")
                        .queryParam("select", "*,images:product_images(*),colors:product_colors(*,info:name_colors(*))").build())
                .retrieve().bodyToFlux(Product.class).collectList().block();
    }
}