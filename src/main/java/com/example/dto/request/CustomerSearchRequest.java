package com.example.dto.request;

import lombok.Data;

@Data
public class CustomerSearchRequest {

    private Integer pageNo;
    private Integer pageSize;
    private String sortProperty;
}
