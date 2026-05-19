package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.request.DeleteCartReq;
import com.thienlong.vppbackend.model.dto.respone.CartRes;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.entity.Cart;
import com.thienlong.vppbackend.repository.CartRep;
import com.thienlong.vppbackend.repository.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartSer {
    private final CartRep rep;
    private final UserRep userRep;

    // Get List Cart By Id
    public List<CartRes> getCartById(int userId) {
        return rep.getListCartById(userId);
    }

    // Update Quantity Product
    public boolean updateQuantity(int id, int quantity) {
        return rep.saveQuantityProduct(id, quantity);
    }

    // Get/Create User Cart
    private Cart checkCart(UUID userId, String AT) {
        Cart cart = rep.checkCartForUser(userId, AT);
        if (cart != null) return cart;
        // Create
        rep.createCartForUser(userId, AT); // 200OK -> ()
        return rep.checkCartForUser(userId, AT);
    }

    // Add Product To Cart
    public boolean addToCart(CartReq req, String AT) {
        UserRes user = userRep.getInfoUserByAT(AT);
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
        UserRes user = userRep.getInfoUserByAT(AT);
        UUID userId = user.getId();
        return rep.checkCartForUser(userId, AT);
    }

    // Delete Product In Cart
    public boolean deleteProductInCart(DeleteCartReq req) {
        return rep.deteleCartById(req);
    }
}
