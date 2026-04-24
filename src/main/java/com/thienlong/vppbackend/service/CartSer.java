package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.request.DeleteCartReq;
import com.thienlong.vppbackend.model.dto.respone.CartRes;
import com.thienlong.vppbackend.repository.CartRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSer {
    private final CartRep rep;

    public CartSer(CartRep rep) {
        this.rep = rep;
    }

    // Get List Cart By Id
    public List<CartRes> getCartById(int userId) {
        return rep.getListCartById(userId);
    }

    // Update Quantity Product
    public boolean updateQuantity(int id, int quantity) {
        return rep.saveQuantityProduct(id, quantity);
    }

    // Add Product To Cart
    public boolean addToCart(CartReq cart) {
        return rep.addProductToCart(cart);
    }

    // Delete Product In Cart
    public boolean deleteProductInCart(DeleteCartReq req) {
        return rep.deteleCartById(req);
    }
}
