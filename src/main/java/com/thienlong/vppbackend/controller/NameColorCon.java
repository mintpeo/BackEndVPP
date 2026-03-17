package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.NameColor;
import com.thienlong.vppbackend.service.NameColorSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/name_colors")
public class NameColorCon {
    private final NameColorSer ser;

    public NameColorCon(NameColorSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<NameColor> getAllNameColors() {
        return ser.getAllNameColors();
    }
}
