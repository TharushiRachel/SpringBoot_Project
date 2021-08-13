package com.example.dto;

import com.example.entity.Admin;

public class AdminViewResponse {

    public Object fetchAdminDetails(Admin admin) {
        admin.setId(admin.getId());
        admin.setNic(admin.getNic());
        admin.setFullName(admin.getFullName());
        admin.setEmail(admin.getEmail());
        admin.setPassword(admin.getPassword());
        return admin;
    }

}
