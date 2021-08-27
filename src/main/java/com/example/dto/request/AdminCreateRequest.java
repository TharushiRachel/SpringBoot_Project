package com.example.dto.request;

import lombok.Data;
import lombok.NonNull;
import javax.validation.constraints.*;

@Data
public class AdminCreateRequest {

    @NotNull
    @Pattern(regexp = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$",message = "Invalid NIC")
    private String nic;

    @NotNull
    @Size(min = 2, max = 50 , message = "The name should have atleast 2 characters")
    private String fullName;

    @NotNull
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;
}
