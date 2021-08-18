package com.example.dto.response;

import com.example.entity.Admin;

public class AdminDeleteResponse {

    public Admin deleteAdmin(int id){
        Admin response = new Admin();
        response.setId(id);
        return response;
    }
}
