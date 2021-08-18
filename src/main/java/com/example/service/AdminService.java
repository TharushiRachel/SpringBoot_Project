package com.example.service;

import com.example.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    List<Admin> get(int pageNo, int pageSize, String email);

    Admin findAdminById(int id);

    List<Admin> getAdminList();

    Admin deleteAdmin(int id);


}
