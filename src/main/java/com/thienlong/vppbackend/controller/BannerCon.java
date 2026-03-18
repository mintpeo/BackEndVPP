package com.thienlong.vppbackend.controller;

import com.thienlong.vppbackend.model.Banner;
import com.thienlong.vppbackend.service.BannerSer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
@CrossOrigin(origins = "${app.frontend.url}")
public class BannerCon {
    private final BannerSer ser;

    public BannerCon(BannerSer ser) {
        this.ser = ser;
    }

    @GetMapping("/all")
    public List<Banner> getAllBanners() {
        return ser.getAllBanners();
    }
}
