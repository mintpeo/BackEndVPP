package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

@Data
public class CartRes {
    private ProductInCartRes product;
    private int quantity;
}
