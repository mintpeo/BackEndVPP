package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.ProductImage;
import com.thienlong.vppbackend.service.ProductImageSer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product_images")
@CrossOrigin(origins = "${app.frontend.url}")
public class ProductImageCon {
    private final ProductImageSer ser;

    public ProductImageCon(ProductImageSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<ProductImage> getAllProductColors() {
        return ser.getAllProductImage();
    }
}
