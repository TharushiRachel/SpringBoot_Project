package com.example.service;

import com.example.dto.request.CustomerSearchRequest;
import com.example.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer) throws Exception;

    List<Customer> getCustomerList();

    Customer findCustomerById(int id);

    Page<Customer> search(CustomerSearchRequest request);

    Integer delete(int id);

    Customer update(Customer customer) throws Exception;
}
