package com.example.demo.service;

import com.example.demo.dto.AdminRequest;
import com.example.demo.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Admin save(AdminRequest admin);

}
