package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.dto.request.CartReq;
import com.thienlong.vppbackend.model.dto.request.DeleteCartReq;
import com.thienlong.vppbackend.model.dto.respone.CartRes;
import com.thienlong.vppbackend.model.entity.Cart;
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
    public List<CartRes> getCartById(@RequestHeader String AT) {
        return ser.getCartById(AT);
    }

    @PatchMapping("/quantity")
    public boolean updateQuantityProduct(@RequestParam int id, @RequestParam int quantity) {
        return ser.updateQuantity(id, quantity);
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody CartReq req, @RequestHeader String AT) {
        return ser.addToCart(req, AT);
    }

    @GetMapping("/get")
    public Cart check(@RequestHeader String AT) {
        return ser.check(AT);
    }

    @DeleteMapping("/delete")
    public boolean deleteProduct(@RequestBody DeleteCartReq req) {
        return ser.deleteProductInCart(req);
    }
}
