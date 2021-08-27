package com.example.repository;

import com.example.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, QuerydslPredicateExecutor<Admin> {



}
