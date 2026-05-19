package com.thienlong.vppbackend.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SignUserWithToken {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private String lastName;
}
