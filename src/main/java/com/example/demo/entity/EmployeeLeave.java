package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "emp_leave")
public class EmployeeLeave implements Serializable {

    @Id
    private int empId;

    @Id
    private int adId;

    private Date apply_date;

    private Date start_date;

    private Date end_date;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
