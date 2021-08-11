package com.example.service.impl;

import com.example.dto.AdminRequest;
import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin save(AdminRequest request) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QAdmin.admin.nic.eq(request.getNic()));
        booleanBuilder.and(QAdmin.admin.email.eq(request.getEmail()));
        Iterable<Admin> admins = adminRepository.findAll(booleanBuilder);



        Admin admin = new Admin();
        //set all values from request to this new admin object
        admin.setNic(request.getNic());
        admin.setFullName(request.getFullName());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());

        return adminRepository.save(admin);
    }


}
