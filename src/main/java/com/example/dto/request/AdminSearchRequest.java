package com.example.dto.request;

import lombok.Data;

/**
 * @author Pasindu Lakmal
 */
@Data
public class AdminSearchRequest {

    private Integer pageNo;
    private Integer pageSize;
    private String sortProperty;

}
