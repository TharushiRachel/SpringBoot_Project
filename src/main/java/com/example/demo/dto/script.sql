create
database hms;

use
hms;

create table admin
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    nic       VARCHAR(10) NOT NULL,
    full_name varchar(45),
    email     varchar(45),
    password  varchar(20),
    constraint admin_PK PRIMARY KEY (id)
);

create table customer
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    nic        VARCHAR(10) NOT NULL,
    full_name  varchar(45),
    address    varchar(60),
    dob        date,
    contact_no INT,
    email      varchar(45),
    password   varchar(20),
    admin_id   INT         NOT NUll,
    constraint customer_PK PRIMARY KEY (id),
    constraint fk_customer foreign key (admin_id) references admin (id)
);

create table room
(
    `id`           INT NOT NULL AUTO_INCREMENT,
    num_of_Rooms   int,
    check_in_date  date,
    check_out_date date,
    cust_id        int not null,
    constraint room_PK PRIMARY KEY (id),
    constraint fk_room foreign key (cust_id) references customer (id)
);

create table hall
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    reserve_date date,
    cust_id      int not null,
    constraint hall_PK PRIMARY KEY (id),
    constraint fk_hall foreign key (cust_id) references customer (id)
);

create table employee
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    nic       VARCHAR(10) NOT NULL,
    full_name varchar(45),
    address   varchar(60),
    admin_id  INT         NOT NUll,
    constraint employee_PK PRIMARY KEY (id),
    constraint fk_employee foreign key (admin_id) references admin (id)
);

create table user_account
(
    emp_id     INT         NOT NULL,
    nic        VARCHAR(10) NOT NULL,
    dob        date,
    contact_no int,
    email      varchar(45),
    password   varchar(20),
    constraint employee_PK PRIMARY KEY (emp_id),
    constraint fk_account foreign key (emp_id) references employee (id)
);

create table emp_leave
(
    emp_id     INT NOT NULL,
    admin_id   INT NOT NUll,
    apply_date date,
    start_date date,
    end_date   date,
    constraint employee_PK PRIMARY KEY (emp_id, admin_id),
    constraint fk1_leave foreign key (emp_id) references employee (id),
    constraint fk2_leave foreign key (admin_id) references admin (id)
);

create table department
(
    `id`      INT NOT NULL AUTO_INCREMENT,
    dept_name varchar(45),
    constraint department_PK PRIMARY KEY (id)
);

create table work
(
    emp_id  int not null,
    dept_id int not null,
    constraint work_PK PRIMARY KEY (emp_id, dept_id),
    constraint fk1_work foreign key (emp_id) references employee (id),
    constraint fk2_work foreign key (dept_id) references department (id)
);


