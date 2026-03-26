package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.respone.CartRes;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Repository
public class CartRep {
    private final WebClient client;

    public CartRep(WebClient client) {
        this.client = client;
    }

     // Get List Cart By Id User
    public List<CartRes> getListCartById(int userId) {
        return client.get().uri(uriBuilder -> uriBuilder.path("/carts")
                .queryParam("user_id", "eq." + userId)
                .queryParam("select", "*,product:products(*)").build())
                .retrieve().bodyToFlux(CartRes.class).collectList().block();
    }

    // Update Quantity Product
    public boolean saveQuantityProduct(int id, int quantity) {
        try {
            client.patch().uri(uriBuilder -> uriBuilder.path("/carts").
                    queryParam("id", "eq." + id).build()).
                    bodyValue(Map.of("quantity", quantity)).retrieve().toBodilessEntity().block();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add To Cart
    public boolean addProductToCart(CartReq cart) {
        try {
            client.post().uri(uriBuilder -> uriBuilder.path("/carts").build())
                    .bodyValue(cart).retrieve().toBodilessEntity().block();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}