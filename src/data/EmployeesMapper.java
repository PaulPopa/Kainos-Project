package data;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.Department;
import model.Employee;
import model.User;

public interface EmployeesMapper {
	@Select("SELECT employee_id, f_name, l_name FROM employee WHERE f_name = #{f_name}")
	Employee getEmployee(@Param("f_name") String f_name);
	
	@Select("SELECT f_name, l_name FROM employee JOIN department using(department_id) WHERE d_name = #{d_name}")
	List<Employee> getDepartmentEmployees(@Param("d_name") String d_name);
	
	@Insert("INSERT INTO employee (employee_id, address, email, bank_account, sort_code, starting_salary, f_name, l_name, salary, nin, department_id)"
			+ "VALUES(#{employee_id}, #{address}, #{email}, #{bank_account}, #{sort_code}, #{starting_salary}, #{f_name}, #{l_name}, #{salary}, #{nin}, #{department_id})")
	void insertEmployee(Employee employee);
	
	@Select("SELECT username, password FROM user WHERE username = #{username}")
	User getUser(@Param("username") String username);
}
