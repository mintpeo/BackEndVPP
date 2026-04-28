package com.thienlong.vppbackend.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SignUserWithToken {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    private int role;
}
