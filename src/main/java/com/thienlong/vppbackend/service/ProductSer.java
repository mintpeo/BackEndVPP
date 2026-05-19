package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.dto.respone.ShowProductColorRes;
import com.thienlong.vppbackend.model.dto.respone.ShowProductRes;
import com.thienlong.vppbackend.model.entity.Product;
import com.thienlong.vppbackend.model.entity.ProductImage;
import com.thienlong.vppbackend.repository.ProductRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSer {
    private final ProductRep rep;

    public ProductSer(ProductRep rep) {
        this.rep = rep;
    }

    public List<Product> getAllProducts() {
        return rep.findAll();
    }

    public List<ShowProductRes> getProductToShow() {
        List<Product> products = rep.findAll();

        List<ShowProductRes> productResList = products.stream().map(product -> {
            List<ShowProductColorRes> colors = product.getColors().stream().map(color -> {
                ShowProductColorRes colorRes = new ShowProductColorRes();
                colorRes.setImage(color.getImage());
                colorRes.setName(color.getInfo().getName());
                colorRes.setCode(color.getInfo().getCode());
                return colorRes;
            }).toList();

            List<String> images = product.getImages().stream().map(ProductImage::getImage).toList();

            return new ShowProductRes(product.getId(),
                    product.getSku(),
                    product.getName(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getOriginalPrice(),
                    product.getCurrency(),
                    product.getStock(),
                    product.getRating(),
                    product.getReview(),
                    product.getDescription(),
                    colors,
                    images);
        }).toList();

        return productResList;
    }

    // Find Product -> Detail
    public ShowProductRes getProductById(Long productId) {
        Product product = rep.findProductById(productId);
        List<ShowProductColorRes> colors = product.getColors().stream().map(color -> {
            ShowProductColorRes colorRes = new ShowProductColorRes();
            colorRes.setImage(color.getImage());
            colorRes.setName(color.getInfo().getName());
            colorRes.setCode(color.getInfo().getCode());
            return colorRes;
        }).toList();

        List<String> images = product.getImages().stream().map(ProductImage::getImage).toList();

        return new ShowProductRes(product.getId(),
                product.getSku(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getPrice(),
                product.getOriginalPrice(),
                product.getCurrency(),
                product.getStock(),
                product.getRating(),
                product.getReview(),
                product.getDescription(),
                colors,
                images
        );
    }
}
