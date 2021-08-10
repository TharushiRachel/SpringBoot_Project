package com.example.controller;

import com.example.dto.AdminRequest;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;


    @PostMapping("${app.endpoint.adminSearch}")
    public void addAdmin(@Validated @RequestBody AdminRequest request) {
        adminService.save(request);
    }
}
