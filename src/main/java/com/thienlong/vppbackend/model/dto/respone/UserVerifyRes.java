package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserVerifyRes {
    private Long id;
    private String email;
    private String code;
    private OffsetDateTime expiryTime;
    private boolean verified;
}
