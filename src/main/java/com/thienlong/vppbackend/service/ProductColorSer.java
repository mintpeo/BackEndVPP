package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.ProductColor;
import com.thienlong.vppbackend.repository.ProductColorRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductColorSer {
    private final ProductColorRes res;

    public ProductColorSer(ProductColorRes res) {
        this.res = res;
    }

    public List<ProductColor> getAllProductColors() {
        return res.findAll();
    }
}
