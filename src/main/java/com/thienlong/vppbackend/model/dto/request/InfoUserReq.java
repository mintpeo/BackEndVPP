package com.thienlong.vppbackend.model.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class InfoUserReq {
    private String accToken;
    private UUID id;
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private LocalDate date;
    private String address;
}
