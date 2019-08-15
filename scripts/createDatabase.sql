-- WARNING: This script will nuke the company database!!
drop database if exists company;
create database company;
use company;

create table department(
    department_id char(4) primary key,
    d_name varchar(50) 
);

create table employee(
    employee_id char(8) primary key,
    address varchar(80) not null,
    email varchar(50) unique not null,
    bank_account varchar(8),
    sort_code char(6),
    starting_salary mediumint,
    f_name varchar(50) not null,
    l_name varchar(50) not null,
    salary mediumint,
    nin char(9) unique,
    department_id char(4),
    foreign key (department_id) references department(department_id)
);

create table salesEmployee(
    employee_id char(8) primary key,
    commision_rate decimal(6,3),
    sales_for_period smallint unsigned,
    foreign key (employee_id) references employee(employee_id)
);

create table project(
   project_id mediumint auto_increment primary key,
   project_name varchar(32),
   leader_id char(8),
   foreign key (leader_id) references employee(employee_id)
);

create table assignment(
    employee_id char(8),
    project_id mediumint auto_increment,
    start_date date,
    end_date date,
    primary key (employee_id, project_id, start_date),
    foreign key (employee_id) references employee(employee_id),
    foreign key (project_id) references project(project_id)
);

create table user(
   username char(50) primary key,
   password char(50)
);

INSERT INTO user VALUES('hrteam', 'password');

INSERT INTO department VALUES('SALE', 'Sales');
INSERT INTO department VALUES('TECH', 'Technology');

INSERT INTO employee VALUES ('AB123HE8', '28 Kenthouse road', 'joker@diamond.com', '93847584', '123456', 30000, 'Joker', 'Batman', 30000, '37GH90OGT', 'SALE');
INSERT INTO employee VALUES ('AB123HS7', '22 Stone road', 'jane@diamond.com', '93849258', '123256', 30000, 'Jane', 'Batman', 30000, '37GH90OHG', 'SALE');
INSERT INTO employee VALUES ('BC123HE8', '2 Lilly road', 'ally@diamond.com', '93847584', '923456', 50000, 'Ally', 'Stone', 30000, '37XH90OGT', 'TECH');




