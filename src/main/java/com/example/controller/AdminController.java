package com.example.controller;

import com.example.dto.AdminCreateResponse;
import com.example.dto.AdminRequest;
import com.example.dto.AdminViewResponse;
import com.example.entity.Admin;
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

    @PostMapping("${app.endpoint.adminSearch}")
    public ResponseEntity<Object> addAdmin(@Validated @RequestBody AdminRequest request, AdminCreateResponse response) throws Exception {
        Admin admin = modelMapper.map(request, Admin.class);
        adminService.save(admin);
        return new ResponseEntity<>(response.fetchAdmin(admin.getId()), HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminCreate}")
    public List<Admin> getAdmin(){
        return adminService.get();
    }

    @GetMapping("${app.endpoint.adminView}/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id, AdminViewResponse response) {
        Optional<Admin> admin = adminService.findAdminById((int) id);
        return new ResponseEntity<>(response.fetchAdminDetails(admin.get()), HttpStatus.OK);
    }

    @DeleteMapping("${app.endpoint.adminSearch}/{id}")
    public void deleteAdmin(@PathVariable int id){
        adminService.delete(id);
    }
}
