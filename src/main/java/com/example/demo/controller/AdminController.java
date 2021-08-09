package com.example.demo.controller;

import com.example.demo.dto.AdminRequest;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "admin")
public class AdminController {


    @Autowired
    private AdminService adminService;


    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody AdminRequest request) {
        adminService.save(request);
    }
}
