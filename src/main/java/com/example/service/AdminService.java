package com.example.service;

import com.example.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    List<Admin> get();

    Optional<Admin> findAdminById(int id);


}
