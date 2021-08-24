package com.example.repository;

import com.example.entity.Admin;
import com.example.enum_.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, QuerydslPredicateExecutor<Admin> {



}
