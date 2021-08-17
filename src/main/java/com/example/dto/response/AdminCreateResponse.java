package com.example.dto.response;

import com.example.entity.Admin;

public class AdminCreateResponse {

    public Admin fetchAdmin(int id){
        Admin response = new Admin();
        response.setId(id);
        return response;
    }

}
