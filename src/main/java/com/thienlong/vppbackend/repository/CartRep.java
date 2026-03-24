package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.request.ProductInCartReq;
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
            return false;
        }
    }

    // Add Product To table product_in_cart
    public boolean saveProductInCart(ProductInCartReq product) {
        try {
            client.post().uri("/product_in_cart").bodyValue(product).retrieve().toBodilessEntity().block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Add To Cart
    public boolean addProductInCart(CartReq cart) {
        try {
            client.post().uri("/carts").bodyValue(cart).retrieve().toBodilessEntity().block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}