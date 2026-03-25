package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

@Data
public class CartRes {
    private Integer id;
    private Integer user_id;
    private Integer quantity;
    private Integer product_id;
    private String image;
    private String type;
    private ProductInCartRes product;
}
