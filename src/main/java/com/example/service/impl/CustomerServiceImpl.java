package com.example.service.impl;

import com.example.entity.Admin;
import com.example.entity.Customer;
import com.example.entity.QCustomer;
import com.example.exception.ApiRequestException;
import com.example.repository.AdminRepository;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Customer save(Customer customer) throws Exception {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QCustomer.customer.nic.eq(customer.getNic()));
        booleanBuilder.or(QCustomer.customer.email.eq(customer.getEmail()));
        List<Customer> customers = (List<Customer>) customerRepository.findAll(booleanBuilder);

        if (customers.size() > 0) {
            throw new ApiRequestException("Customer Already Exists");
        }


        Admin adminDb = adminRepository.findById(customer.getAdmin().getId()).orElseThrow(() -> {
            throw new ApiRequestException("Admin with " + customer.getAdmin().getId() + " does not exists");
        });

        customer.setAdmin(adminDb);
        return customerRepository.save(customer);

    }
}
