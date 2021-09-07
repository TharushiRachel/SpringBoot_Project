package com.example.service.impl;

import com.example.dto.request.CustomerSearchRequest;
import com.example.entity.Admin;
import com.example.entity.Customer;
import com.example.entity.QCustomer;
import com.example.enums.Status;
import com.example.exception.ApiRequestException;
import com.example.repository.AdminRepository;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        customer.setStatus(Status.valueOf("ACTIVE"));
        customer.setAdmin(adminDb);

        String password = customer.getPassword();
        String encryptedPwd = passwordEncoder.encode(password);
        customer.setPassword(encryptedPwd);

        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(int id) {

        return customerRepository.findById(id).orElseThrow(() -> {
            throw new ApiRequestException("Customer with " + id + " does not exists");
        });
    }

    @Override
    public Page<Customer> search(CustomerSearchRequest request) {
        Pageable paging = PageRequest.of(request.getPageNo() + 1, request.getPageSize(), Sort.by(request.getSortProperty()));
        return customerRepository.findAll(paging);
    }

    @Override
    public Integer delete(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            throw new ApiRequestException("Customer with " + id + " does not exists");
        });

        customer.setStatus(Status.DELETED);
        return customer.getId();
    }

    @Override
    public Customer update(Customer customer) throws Exception {

        Customer customerDb = customerRepository.findById(customer.getId()).orElseThrow(() -> {
            throw new ApiRequestException("Customer with " + customer.getId() + " does not exists");
        });

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QCustomer.customer.nic.eq(customer.getNic()));
        booleanBuilder.or(QCustomer.customer.email.eq(customer.getEmail()));
        List<Customer> customers = (List<Customer>) customerRepository.findAll(booleanBuilder);

        if (customers.size() > 1) {
            throw new ApiRequestException("Customer already exists");
        }

        customer.setStatus(Status.valueOf("ACTIVE"));

        customerDb.setNic(customer.getNic());
        customerDb.setFullName(customer.getFullName());
        customerDb.setAddress(customer.getAddress());
        customerDb.setContactNo(customer.getContactNo());
        customerDb.setDob(customer.getDob());
        customerDb.setEmail(customer.getEmail());
        customerDb.setPassword(customer.getPassword());

        return customerRepository.save(customerDb);

    }


}
