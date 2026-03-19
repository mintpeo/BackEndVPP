package com.thienlong.vppbackend.model.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private LocalDate date;
    private String address;
}
