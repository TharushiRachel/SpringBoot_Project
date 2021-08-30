package com.example.controller;

import com.example.dto.request.CustomerCreateRequest;
import com.example.dto.response.CustomerCreateResponse;
import com.example.entity.Admin;
import com.example.entity.Customer;
import com.example.repository.AdminRepository;
import com.example.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.customerCreate}")
    public ResponseEntity<Object> saveCustomer(@Validated @RequestBody CustomerCreateRequest request) throws Exception{
        Customer customer = modelMapper.map(request, Customer.class);
        Customer saveCustomer = customerService.save(customer);
        CustomerCreateResponse customerCreateResponse = modelMapper.map(saveCustomer, CustomerCreateResponse.class);
        return new ResponseEntity<>(customerCreateResponse, HttpStatus.CREATED);
    }
}
