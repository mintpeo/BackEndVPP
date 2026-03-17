package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.User;
import com.thienlong.vppbackend.service.UserSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserCon {
    private final UserSer ser;

    public UserCon(UserSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return ser.getAllUsers();
    }
}
