package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.User;
import com.thienlong.vppbackend.repository.UserRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSer {
    private final UserRes res;

    public UserSer(UserRes res) {
        this.res = res;
    }

    public List<User> getAllUsers() {
        return res.findAll();
    }
}
