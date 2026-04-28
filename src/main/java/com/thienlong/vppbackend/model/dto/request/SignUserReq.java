package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SignUserReq {
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String password;
    private int role = 1;
}
