CREATE USER hr@localhost IDENTIFIED WITH mysql_native_password BY 'hrpass';
GRANT SELECT ON company.* to hr@localhost;
