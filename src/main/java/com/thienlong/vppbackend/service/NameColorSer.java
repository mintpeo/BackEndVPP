package com.thienlong.vppbackend.service;

import com.thienlong.vppbackend.model.NameColor;
import com.thienlong.vppbackend.repository.NameColorRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameColorSer {
    private final NameColorRes res;

    public NameColorSer(NameColorRes res) {
        this.res = res;
    }

    public List<NameColor> getAllNameColors() {
        return res.findAll();
    }
}
