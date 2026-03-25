package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.respone.CartRes;
import com.thienlong.vppbackend.service.CartSer;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "${app.frontend.url}")
public class CartCon {
    private final CartSer ser;

    public CartCon(CartSer ser) {
        this.ser = ser;
    }

    @GetMapping
    public List<CartRes> getCartById(@RequestParam int userId) {
        return ser.getCartById(userId);
    }

    @PatchMapping("/quantity")
    public boolean updateQuantityProduct(@RequestParam int id, @RequestParam int quantity) {
        return ser.updateQuantity(id, quantity);
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody CartReq cart) {
        return ser.addToCart(cart);
    }
}
