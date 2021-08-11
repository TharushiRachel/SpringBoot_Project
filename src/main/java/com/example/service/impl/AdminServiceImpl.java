package com.example.service.impl;

import com.example.dto.AdminRequest;
import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public Admin save(AdminRequest request) throws Exception {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        ModelMapper modelMapper = new ModelMapper();
        booleanBuilder.and(QAdmin.admin.nic.eq(request.getNic()));
        booleanBuilder.or(QAdmin.admin.email.eq(request.getEmail()));
        List<Admin> admins = (List<Admin>) adminRepository.findAll(booleanBuilder);

        if (admins.size()>0) {
            throw new ValidationException("Admin already exists");
        }
            Admin admin = modelMapper.map(request, Admin.class);

        return adminRepository.save(admin);
    }


}
