package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.ProductImage;
import com.thienlong.vppbackend.repository.ProductImageRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageSer {
    private final ProductImageRes res;

    public ProductImageSer(ProductImageRes res) {
        this.res = res;
    }

    public List<ProductImage> getAllProductImage() {
        return res.findAll();
    }
}
