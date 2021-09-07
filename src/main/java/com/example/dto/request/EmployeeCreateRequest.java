package com.example.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class EmployeeCreateRequest {

    @NotNull
    @Pattern(regexp = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$", message = "Invalid NIC")
    private String nic;

    @NotNull
    @Size(min = 2, max = 50, message = "The name should have at least 2 characters")
    private String fullName;

    @NotNull
    @Size(min = 2, max = 50, message = "The address should have at least 2 characters")
    private String address;

    private Admin admin;

    @Data
    private static class Admin {
        private int id;
    }

    private Set<Department> departments;

    @Data
    private static class Department {
        private int id;
    }
}
