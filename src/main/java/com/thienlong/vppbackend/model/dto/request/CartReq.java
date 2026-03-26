package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

@Data
public class CartReq {
    private Integer user_id;
    private Integer product_id;
    private int quantity;
    private String image;
    private String type;
}
