package com.example.controller;

import com.example.dto.AdminRequest;
import com.example.entity.Admin;
import com.example.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("${app.endpoint.adminSearch}")
    public void addAdmin(@Validated @RequestBody AdminRequest request) throws Exception {
        Admin admin = modelMapper.map(request, Admin.class);
        adminService.save(admin);
    }
}
