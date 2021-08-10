package com.example.service.impl;

import com.example.dto.AdminRequest;
import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin save(AdminRequest request) {

        Admin admin = new Admin();
        //set all values from request to this new admin object
        admin.setNic(request.getNic());
        admin.setFullName(request.getFullName());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());

//        request.setNic(admin.getNic());
//        request.setFullName(admin.getFullName());
//        request.setEmail(admin.getEmail());
//        request.setPassword(admin.getPassword());
        return adminRepository.save(admin);
    }
}
