package com.example.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DepartmentCreateRequest {

    @NotNull
    @Size(min = 2, max = 50 , message = "The name should have atleast 2 characters")
    private String name;
}
