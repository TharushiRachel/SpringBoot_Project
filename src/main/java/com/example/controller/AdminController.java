package com.example.controller;

import com.example.dto.AdminRequest;
import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class AdminController {



    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;



//    public void addAdmin(@Validated @RequestBody AdminRequest request) throws Exception {
//        Admin admin = modelMapper.map(request, Admin.class);
//        adminService.save(admin);
//    }
    @PostMapping("${app.endpoint.adminSearch}")
    public ResponseEntity<Object> addAdmin(@Validated @RequestBody AdminRequest request) throws Exception {
        Admin admin = modelMapper.map(request, Admin.class);
        adminService.save(admin);
        return new ResponseEntity<>(request.getNic(), HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminSearch}")
    public List<Admin> getAdmin(){
        return adminService.get();
    }

    @GetMapping("${app.endpoint.adminSearch}/{id}")
    public ResponseEntity<Admin> getById(@PathVariable long id) {
        Optional<Admin> admin = adminService.findAdminById((int) id);
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);
    }

}
