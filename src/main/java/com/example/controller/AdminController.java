package com.example.demo.controller;

import com.example.demo.dto.AdminRequest;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;


    @PostMapping("${app.endpoint.adminSearch}")
    public void addAdmin(@RequestBody AdminRequest request) {
        adminService.save(request);
    }
}
