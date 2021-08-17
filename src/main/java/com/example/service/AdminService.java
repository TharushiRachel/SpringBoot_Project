package com.example.service;

import com.example.dto.response.AdminSuggestionResponse;
import com.example.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    List<Admin> get(int pageNo, int pageSize, String email);

    Admin findAdminById(int id);

    List<Admin> getAdminList();

    void delete(int id);


}
