package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.dto.request.LoginUserReq;
import com.thienlong.vppbackend.model.dto.request.PassChangeReq;
import com.thienlong.vppbackend.model.dto.respone.UserRes;
import com.thienlong.vppbackend.model.entity.User;
import com.thienlong.vppbackend.model.dto.request.SignUserReq;
import com.thienlong.vppbackend.service.UserSer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "${app.frontend.url}")
public class UserCon {
    private final UserSer ser;

    public UserCon(UserSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return ser.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserReq user) {
        UserRes res = ser.loginUser(user);

        if (res != null) return ResponseEntity.ok(res);
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai email hoặc mật khẩu");
    }

    @PostMapping("/sign")
    public SignUserReq signUser(@RequestBody SignUserReq dto) {
        return ser.signUser(dto);
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
