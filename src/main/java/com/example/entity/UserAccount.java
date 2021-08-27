package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    private int empId;

    private String nic;

    private Date dob;

    private int contactNo;

    private String email;

    private String password;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
