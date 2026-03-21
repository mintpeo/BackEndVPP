package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.respone.UserWithCartRes;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class CartRep {
    private final WebClient client;

    public CartRep(WebClient client) {
        this.client = client;
    }

     // Get List Cart By Email User
    public UserWithCartRes getListCartByEmail(String email) {
        return client.get().uri(uriBuilder -> uriBuilder.path("/users")
                .queryParam("email", "eq." + email)
                .queryParam("select", "email,carts:carts(quantity, product:product_in_cart(name, price, originalPrice, image, type))").build())
                .retrieve().bodyToFlux(UserWithCartRes.class).blockFirst();
    }
}