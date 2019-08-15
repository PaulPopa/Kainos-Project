-- WARNING: This script will nuke the company database!!
drop database if exists company;
create database company;
use company;

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
    nin char(9) unique
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
