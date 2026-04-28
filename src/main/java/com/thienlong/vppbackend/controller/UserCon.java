package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.dto.request.LoginUserReq;
import com.thienlong.vppbackend.model.dto.request.PassChangeReq;
import com.thienlong.vppbackend.model.dto.SignUserWithToken;
import com.thienlong.vppbackend.model.dto.request.SignUserReq;
import com.thienlong.vppbackend.service.UserSer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "${app.frontend.url}")
public class UserCon {
    private final UserSer ser;

    public UserCon(UserSer ser) {
        this.ser = ser;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserReq user) {
        try {
            SignUserWithToken res = ser.loginUser(user);
            return ResponseEntity.ok(res);
        } catch (WebClientResponseException.BadRequest e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai email hoặc mật khẩu");
        }
    }

    @PostMapping("/sign")
    public SignUserWithToken signUser(@RequestBody SignUserReq req) {
        return ser.signUser(req);
    }

    @GetMapping("/checkEmail")
    public boolean checkEmail(@RequestParam String email) {
        return ser.emailCheck(email);
    }

    @PatchMapping("/changePass")
    public boolean changePass(@RequestBody PassChangeReq user) {
        return ser.changePass(user);
    }
}
