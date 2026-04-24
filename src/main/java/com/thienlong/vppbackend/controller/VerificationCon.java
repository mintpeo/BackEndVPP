package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.service.VerificationSer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "${app.frontend.url}")
public class VerificationCon {
    private final VerificationSer ser;

    public VerificationCon(VerificationSer ser) {
        this.ser = ser;
    }

    @PostMapping("/sendCode")
    public boolean sendCode(@RequestParam String email) {
        return ser.sendVerificationCode(email);
    }

    @PostMapping("/verifyCode")
    public boolean verify(@RequestParam String email, @RequestParam String code) {
        return ser.verifyCode(email, code);
    }
}
