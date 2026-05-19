package com.thienlong.vppbackend.repository;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.request.DeleteCartReq;
import com.thienlong.vppbackend.model.entity.Cart;
import com.thienlong.vppbackend.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CartRep {
    private final WebClient guestsClient;

    public CartRep(@Qualifier("guestsClient") WebClient guestsClient) {
        this.guestsClient = guestsClient;
    }

     // Get List Cart By Id User
    public Cart getListCartById(String AT) {
        return guestsClient.get().uri(uriBuilder -> uriBuilder.path("/carts")
                .queryParam("select", "*,items:cart_item(*)").build())
                .header("Authorization", "Bearer " + AT)
                .retrieve().bodyToFlux(Cart.class).blockFirst();
    }

    // Update Quantity Product
    public boolean saveQuantityProduct(int id, int quantity) {
        try {
            guestsClient.patch().uri(uriBuilder -> uriBuilder.path("/carts").
                    queryParam("id", "eq." + id).build()).
                    bodyValue(Map.of("quantity", quantity)).retrieve().toBodilessEntity().block();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check Cart -> Null -> Create Cart
    public Cart checkCartForUser(String AT) {
        return guestsClient.get().uri("/carts")
                .header("Authorization", "Bearer " + AT)
                .retrieve().bodyToFlux(Cart.class).blockFirst();
    }
    public Cart createCartForUser(UUID userId, String AT) {
        Map<String, UUID> body = Map.of(
                "user_id", userId
        );

        return guestsClient.post().uri(uriBuilder -> uriBuilder.path("/carts")
                        .queryParam("user_id", "eq." + userId)
                        .build())
                .header("Authorization", "Bearer " + AT)
                .bodyValue(body)
                .retrieve().bodyToFlux(Cart.class).blockFirst();
    }

    // Add To Cart
    public CartItem addProductToCart(CartReq req, String AT) {
        Map<String, Object> body = Map.of(
                "cart_id", req.getCartId(),
                "product_id", req.getProductId(),
                "image", req.getImage(),
                "quantity", req.getQuantity(),
                "type", req.getType(),
                "name", req.getName(),
                "price", req.getPrice(),
                "originalPrice", req.getOriginalPrice(),
                "currency", req.getCurrency()
        );

        return guestsClient.post().uri(uriBuilder -> uriBuilder.path("/cart_item").build())
                .header("Authorization", "Bearer " + AT)
                .bodyValue(body).retrieve().bodyToFlux(CartItem.class).blockFirst();
    }

    // Delete Cart By Id
    public boolean deteleCartById(DeleteCartReq req) {
        ResponseEntity<String> res = guestsClient.delete().uri(uriBuilder -> uriBuilder.path("/carts").
                queryParam("id", "eq." + req.getId()).
                queryParam("user_id", "eq." + req.getUserId()).build()).
                header("Prefer", "return=representation").
                retrieve().toEntity(String.class).block();
        return res != null && res.getStatusCode().is2xxSuccessful() &&
                res.getBody() != null &&
                !res.getBody().equals("[]");
    }
}