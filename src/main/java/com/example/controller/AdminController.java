package com.example.controller;

import com.example.dto.AdminCreateResponse;
import com.example.dto.AdminRequest;
import com.example.dto.AdminViewResponse;
import com.example.entity.Admin;
import com.example.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("${app.endpoint.adminCreate}")
    public ResponseEntity<Object> addAdmin(@Validated @RequestBody AdminRequest request, AdminCreateResponse response) throws Exception {
        Admin admin = modelMapper.map(request, Admin.class);
        adminService.save(admin);
        return new ResponseEntity<>(response.fetchAdmin(admin.getId()), HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminSearch}")
    public ResponseEntity<List<Admin>> getAdmin( @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "email") String email){
       List<Admin> list = adminService.get(pageNo,pageSize,email);
        return new ResponseEntity<List<Admin>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminView}")
    public ResponseEntity<Object> getById(@PathVariable int id, AdminViewResponse response) {
        Optional<Admin> admin = adminService.findAdminById((int) id);
        return new ResponseEntity<>(response.fetchAdminDetails(admin.get()), HttpStatus.OK);
    }

    @DeleteMapping("${app.endpoint.adminDelete}")
    public void deleteAdmin(@PathVariable int id){
        adminService.delete(id);
    }
}
