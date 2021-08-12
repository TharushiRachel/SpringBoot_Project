package com.example.service.impl;

import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.List;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin save(Admin admin) throws Exception {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QAdmin.admin.nic.eq(admin.getNic()));
        booleanBuilder.or(QAdmin.admin.email.eq(admin.getEmail()));
        List<Admin> admins = (List<Admin>) adminRepository.findAll(booleanBuilder);

        if (admins.size() > 0) {
            throw new ValidationException("Admin already exists");
        }
        return adminRepository.save(admin);
    }


}
