package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CartReq {
    private Long cartId;
    private UUID userId;
    private long productId;
    private int quantity;
    private String type;
}