package com.example.controller;

import com.example.dto.response.AdminCreateResponse;
import com.example.dto.request.AdminRequest;
import com.example.dto.response.AdminDeleteResponse;
import com.example.dto.response.AdminSuggestionResponse;
import com.example.dto.response.AdminViewResponse;
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
import java.util.stream.Collectors;

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
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminView}")
    public ResponseEntity<AdminViewResponse> getById(@PathVariable int id) {
        Admin admin = adminService.findAdminById((id));
        AdminViewResponse adminViewResponse = modelMapper.map(admin, AdminViewResponse.class);
        return new ResponseEntity<>(adminViewResponse, HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminSuggestion}")
    public List<AdminSuggestionResponse> getAdminList(){
        List<Admin> adminList = adminService.getAdminList();
       return adminList.stream().map(admin -> modelMapper.map(admin, AdminSuggestionResponse.class)).collect(Collectors.toList());
    }

    @DeleteMapping("${app.endpoint.adminDelete}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable int id, AdminDeleteResponse response){
        Admin admin = adminService.deleteAdmin(id);
        return new ResponseEntity<>(response.deleteAdmin(admin.getId()), HttpStatus.OK);
    }



}
