package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.request.DeleteCartReq;
import com.thienlong.vppbackend.model.dto.respone.CartRes;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.entity.Cart;
import com.thienlong.vppbackend.repository.CartRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartSer {
    private final CartRep rep;
    private final UserSer userSer;

    // Get List Cart By Id
    public List<CartRes> getCartById(String AT) {
        Cart cart = rep.getListCartById(AT);
        return cart.getItems().stream().map(item -> {
            CartRes cartRes = new CartRes();
            cartRes.setId(item.getId());
            cartRes.setType(item.getType());
            cartRes.setImage(item.getImage());
            cartRes.setQuantity(item.getQuantity());
            cartRes.setProductId(item.getProductId());
            cartRes.setName(item.getName());
            cartRes.setPrice(item.getPrice());
            cartRes.setOriginalPrice(item.getOriginalPrice());
            cartRes.setCurrency(item.getCurrency());
            return cartRes;
        }).toList();
    }

    // Update Quantity Product
    public boolean updateQuantity(int id, int quantity) {
        return rep.saveQuantityProduct(id, quantity);
    }

    // Get/Create User Cart
    private Cart checkCart(UUID userId, String AT) {
        Cart cart = rep.checkCartForUser(AT);
        if (cart != null) return cart;
        // Create
        rep.createCartForUser(userId, AT); // 200OK -> ()
        return rep.checkCartForUser(AT);
    }

    // Add Product To Cart
    public boolean addToCart(CartReq req, String AT) {
        UserRes user = userSer.getInfoUserByAT(AT);
        UUID userId = user.getId();
        Cart cart = checkCart(userId, AT); // Get/Create Cart
        if (cart.getId() != null) {
            req.setCartId(cart.getId()); // add cart_id
            req.setUserId(userId); // add user_id
            rep.addProductToCart(req, AT);
            return true;
        }
        return false;
    }

    public Cart check(String AT) {
        return rep.checkCartForUser(AT);
    }

    // Delete Product In Cart
    public boolean deleteProductInCart(DeleteCartReq req) {
        return rep.deteleCartById(req);
    }
}
