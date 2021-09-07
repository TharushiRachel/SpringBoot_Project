package com.example.controller;

import com.example.dto.request.AdminCreateRequest;
import com.example.dto.request.AdminSearchRequest;
import com.example.dto.request.AdminUpdateRequest;
import com.example.dto.response.*;
import com.example.entity.Admin;
import com.example.enums.Status;
import com.example.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.adminCreate}")
    public ResponseEntity<Object> saveAdmin(@Validated @RequestBody AdminCreateRequest request) throws Exception {
        Admin admin = modelMapper.map(request, Admin.class);
        Admin savedAdmin = adminService.save(admin);
        AdminCreateResponse adminCreateResponse = modelMapper.map(savedAdmin, AdminCreateResponse.class);
        return new ResponseEntity<>(adminCreateResponse, HttpStatus.CREATED);
    }


    @GetMapping("${app.endpoint.adminSearch}")
    public ResponseEntity<List<AdminSearchResponse>> search(@Validated AdminSearchRequest adminSearchRequest) {
        Page<Admin> adminPage = adminService.search(adminSearchRequest);
        List<AdminSearchResponse> adminSearchResponses = adminPage.stream().map(admin -> modelMapper.map(admin, AdminSearchResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(adminSearchResponses, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminView}")
    public ResponseEntity<AdminViewResponse> viewAdmin(@PathVariable int id) {
        Admin admin = adminService.findAdminById((id));
        AdminViewResponse adminViewResponse = modelMapper.map(admin, AdminViewResponse.class);
        return new ResponseEntity<>(adminViewResponse, HttpStatus.OK);
    }

    @GetMapping("${app.endpoint.adminSuggestion}")
    public ResponseEntity<List<AdminSuggestionResponse>> listAdmin(Status status) {
        List<Admin> adminList = adminService.getAdminList();
        List<AdminSuggestionResponse> adminSuggestionResponses = adminList.stream().map(admin -> modelMapper.map(admin, AdminSuggestionResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(adminSuggestionResponses, HttpStatus.OK);
    }

    @DeleteMapping("${app.endpoint.adminDelete}")
    public ResponseEntity<AdminDeleteResponse> deleteAdmin(@PathVariable int id) {
        Integer deletedAdminId = adminService.deleteAdmin(id);
        AdminDeleteResponse adminDeleteResponse = new AdminDeleteResponse();
        adminDeleteResponse.setId(deletedAdminId);
        return new ResponseEntity<>(adminDeleteResponse, HttpStatus.OK);
    }

    @PutMapping("${app.endpoint.adminUpdate}")
    public ResponseEntity<AdminUpdateResponse> updateAdmin(@PathVariable int id, @Validated @RequestBody AdminUpdateRequest request) throws Exception {
        Admin admin = modelMapper.map(request, Admin.class);
        admin.setId(id);
        Admin updateAdmin = adminService.update(admin);
        AdminUpdateResponse adminUpdateResponse = modelMapper.map(updateAdmin, AdminUpdateResponse.class);
        return new ResponseEntity<>(adminUpdateResponse, HttpStatus.OK);
    }

}
