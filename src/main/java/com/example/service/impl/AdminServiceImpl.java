package com.example.service.impl;

import com.example.dto.request.AdminSearchRequest;
import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.exception.ApiRequestException;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Admin save(Admin admin) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QAdmin.admin.nic.eq(admin.getNic()));
        booleanBuilder.or(QAdmin.admin.email.eq(admin.getEmail()));
        List<Admin> admins = (List<Admin>) adminRepository.findAll(booleanBuilder);

        if (admins.size() > 0) {
            throw new ApiRequestException("Admin Already Exists");
        }
        return adminRepository.save(admin);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Admin> search(AdminSearchRequest request) {
        Pageable paging = PageRequest.of(request.getPageNo() + 1, request.getPageSize(), Sort.by(request.getSortProperty()));
        return adminRepository.findAll(paging);
    }

    @Transactional(readOnly = true)
    @Override
    public Admin findAdminById(int id) {

        return adminRepository.findById(id).orElseThrow(() -> {
            throw new ApiRequestException("Admin with " + id + " does not exists");
        });
    }

    @Override
    public List<Admin> getAdminList() {
        return adminRepository.findAll();
    }


    @Override
    public Integer deleteAdmin(int id) {

        Admin admin = adminRepository.findById(id).orElseThrow(() -> {
            throw new ApiRequestException("Admin with " + id + " does not exists");
        });
        adminRepository.delete(admin);
        return admin.getId();
    }

    @Override
    public Admin update( Admin admin) throws Exception {

        Admin adminDb = adminRepository.findById(admin.getId()).orElseThrow(() -> {
            throw new ApiRequestException("Admin with " + admin.getId() + " does not exists");
        });

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QAdmin.admin.nic.eq(admin.getNic()));
        booleanBuilder.or(QAdmin.admin.email.eq(admin.getEmail()));
        List<Admin> admins = (List<Admin>) adminRepository.findAll(booleanBuilder);

        if (admins.size() > 1) {
            throw new ApiRequestException("Admin already exists");
        }

        adminDb.setEmail(admin.getEmail());
        adminDb.setNic(admin.getNic());
        adminDb.setFullName(admin.getFullName());
        adminDb.setPassword(admin.getPassword());

        return adminRepository.save(adminDb);

    }


}
