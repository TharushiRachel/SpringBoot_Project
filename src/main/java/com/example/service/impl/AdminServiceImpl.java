package com.example.service.impl;

import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Transactional(readOnly = true)
    @Override
    public List<Admin> get(int pageNo, int pageSize, String email) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(email));
        Page<Admin> adminPage = adminRepository.findAll(paging);

        if(adminPage.hasContent()){
            return adminPage.getContent();
        }
        else {
            return new ArrayList<Admin>();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Admin> findAdminById(int id) {
        boolean exists = adminRepository.existsById(Integer.valueOf(id));
        if(!exists){
            throw  new IllegalStateException("Admin with "+id+" does not exists");
        }
        return adminRepository.findById(Integer.valueOf(id));
    }

    @Override
    public void delete(int id) {
        boolean exists= adminRepository.existsById(Integer.valueOf(id));
        if(!exists){
            throw  new IllegalStateException("Admin with id "+id+" does not exists");
        }
        adminRepository.deleteById(Integer.valueOf(id));
    }


}
