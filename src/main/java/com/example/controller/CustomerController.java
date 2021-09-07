package com.example.controller;

import com.example.dto.request.CustomerCreateRequest;
import com.example.dto.request.CustomerSearchRequest;
import com.example.dto.request.CustomerUpdateRequest;
import com.example.dto.response.*;
import com.example.entity.Customer;
import com.example.enums.Status;
import com.example.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.customerCreate}")
    public ResponseEntity<Object> saveCustomer(@Validated @RequestBody CustomerCreateRequest request) throws Exception {
        Customer customer = modelMapper.map(request, Customer.class);
        Customer saveCustomer = customerService.save(customer);
        CustomerCreateResponse customerCreateResponse = modelMapper.map(saveCustomer, CustomerCreateResponse.class);
        return new ResponseEntity<>(customerCreateResponse, HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.customerSuggestion}")
    public ResponseEntity<List<CustomerSuggestionResponse>> listCustomer(Status status) {
        List<Customer> customerList = customerService.getCustomerList();
        List<CustomerSuggestionResponse> customerSuggestionResponses = customerList.stream().map(customer -> modelMapper.map(customer, CustomerSuggestionResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(customerSuggestionResponses, HttpStatus.OK);
    }


    @GetMapping("${app.endpoint.customerView}")
    public ResponseEntity<CustomerViewResponse> viewCustomer(@PathVariable int id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerViewResponse customerViewResponse = modelMapper.map(customer, CustomerViewResponse.class);
        return new ResponseEntity<>(customerViewResponse, HttpStatus.OK);
    }


    @GetMapping("${app.endpoint.customerSearch}")
    public ResponseEntity<List<CustomerSearchResponse>> search(@Validated CustomerSearchRequest customerSearchRequest) {
        Page<Customer> customerPage = customerService.search(customerSearchRequest);
        List<CustomerSearchResponse> customerSearchResponses = customerPage.stream().map(customer -> modelMapper.map(customer, CustomerSearchResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(customerSearchResponses, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("${app.endpoint.customerDelete}")
    public ResponseEntity<CustomerDeleteResponse> delete(@PathVariable int id) {
        Integer deletedCustomerId = customerService.delete(id);
        CustomerDeleteResponse customerDeleteResponse = new CustomerDeleteResponse();
        customerDeleteResponse.setId(deletedCustomerId);
        return new ResponseEntity<>(customerDeleteResponse, HttpStatus.OK);
    }

    @PutMapping("${app.endpoint.customerUpdate}")
    public ResponseEntity<CustomerUpdateResponse> updateCustomer(@PathVariable int id, @Validated @RequestBody CustomerUpdateRequest request) throws Exception {
        Customer customer = modelMapper.map(request, Customer.class);
        customer.setId(id);
        Customer customerUpdate = customerService.update(customer);
        CustomerUpdateResponse customerUpdateResponse = modelMapper.map(customerUpdate, CustomerUpdateResponse.class);
        return new ResponseEntity<>(customerUpdateResponse, HttpStatus.OK);
    }
}
