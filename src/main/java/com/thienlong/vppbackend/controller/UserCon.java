package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.User;
import com.thienlong.vppbackend.service.UserSer;
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

    @GetMapping("/login")
    public User checkLogin(@RequestParam String email, @RequestParam String pass) {
        List<User> users = ser.getAllUsers();

        return users.stream().filter(e -> e.getEmail().equals(email))
                .filter(p -> p.getPass().equals(pass)).findFirst().get();
    }
}
