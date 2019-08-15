CREATE USER hr@localhost IDENTIFIED WITH mysql_native_password BY 'hrpass';
GRANT SELECT, INSERT ON company.employee to hr@localhost;

CREATE USER finance@localhost IDENTIFIED WITH mysql_native_password BY 'finpass';
GRANT SELECT ON company.employee to finance@localhost;

CREATE USER sales@localhost IDENTIFIED WITH mysql_native_password BY 'salespass';
GRANT SELECT ON company.salesEmployee to sales@localhost;

CREATE USER talent@localhost IDENTIFIED WITH mysql_native_password BY 'talpass';
GRANT SELECT ON company.projects, company.assignment, (employee_id, f_name, l_name)company.employee to talent@localhost;

