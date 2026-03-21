package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

@Data
public class ProductInCartRes {
    private String name;
    private String image;
    private String type;
    private String price;
    private String originalPrice;
}
