package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.dto.respone.UserWithCartRes;
import com.thienlong.vppbackend.service.CartSer;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "${app.frontend.url}")
public class CartCon {
    private final CartSer ser;

    public CartCon(CartSer ser) {
        this.ser = ser;
    }

    @GetMapping
    public UserWithCartRes getCartByEmail(@RequestParam String email) {
        return ser.getCartByEmail(email);
    }
}
