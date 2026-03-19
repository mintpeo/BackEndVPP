package com.thienlong.vppbackend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassChangeReq {
    private String email;
    private String oldPass;
    private String newPass;
}
