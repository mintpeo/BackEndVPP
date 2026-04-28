package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

@Data
public class LoginUserReq {
    private String email;
    private String password;
}
