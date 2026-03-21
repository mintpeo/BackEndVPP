package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

import java.util.List;

@Data
public class UserWithCartRes {
    private String email;
    private List<CartRes> carts;
}
