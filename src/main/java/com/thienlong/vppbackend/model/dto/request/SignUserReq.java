package com.thienlong.vppbackend.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUserReq {
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String password;
}
