package com.example.dto;

import lombok.Data;

@Data
public class AdminRequest {

    private String nic;

    private String fullName;

    private String email;

    private String password;
}
