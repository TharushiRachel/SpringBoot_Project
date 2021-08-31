package com.example.dto.response;

import lombok.Data;

@Data
public class CustomerSearchResponse {

    private int id;

    private String nic;

    private String fullName;

    private String address;

    private String dob;

    private int contactNo;

    private String email;
}
