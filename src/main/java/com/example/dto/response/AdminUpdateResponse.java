package com.example.dto.response;

import lombok.Data;

@Data
public class AdminUpdateResponse {

    private int id;

    private String nic;

    private String fullName;

    private String email;

    private String password;
}
