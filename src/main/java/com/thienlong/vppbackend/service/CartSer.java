package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.respone.UserWithCartRes;
import com.thienlong.vppbackend.repository.CartRep;
import org.springframework.stereotype.Service;

@Service
public class CartSer {
    private final CartRep rep;

    public CartSer(CartRep rep) {
        this.rep = rep;
    }

    // Get List Cart By Email
    public UserWithCartRes getCartByEmail(String email) {
        return rep.getListCartByEmail(email);
    }
}
