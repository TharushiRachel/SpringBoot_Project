package com.example.service;

import com.example.dto.request.AdminSearchRequest;
import com.example.entity.Admin;
import org.springframework.data.domain.Page;
import java.util.List;

public interface AdminService {

    Admin save(Admin admin) throws Exception;

    Page<Admin> search(AdminSearchRequest request);

    Admin findAdminById(int id);

    List<Admin> getAdminList();

    Integer deleteAdmin(int id);

    Admin update( Admin admin) throws Exception;
}
