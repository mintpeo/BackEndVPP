package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

@Data
public class ProductInCartReq {
    private Integer product_id;
    private String name;
    private String image;
    private String type;
    private Integer price;
    private Integer originalPrice;
}
