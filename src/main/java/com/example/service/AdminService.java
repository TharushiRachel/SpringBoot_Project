package com.example.service;

import com.example.dto.AdminRequest;
import com.example.entity.Admin;
import org.springframework.stereotype.Service;

public interface AdminService {

    Admin save(AdminRequest admin);

}
