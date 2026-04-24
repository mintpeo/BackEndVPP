package com.thienlong.vppbackend.model.dto.respone;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRes {
    private String id;
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private LocalDate date;
    private String address;
}
