package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.dto.respone.SupabaseAuthRes;
import com.thienlong.vppbackend.service.TokenSer;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/token")
@CrossOrigin(origins = "${app.frontend.url}")
public class TokenCon {
    private final TokenSer ser;

    public TokenCon(TokenSer ser) {
        this.ser = ser;
    }

    @PostMapping("/resetAT")
    public SupabaseAuthRes resetATByRT(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refresh_token");
        return ser.resetATByRT(refreshToken);
    }
}
