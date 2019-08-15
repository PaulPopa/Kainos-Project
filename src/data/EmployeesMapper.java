package data;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.Employee;

public interface EmployeesMapper {
	@Select("SELECT employee_id, f_name, l_name FROM employee WHERE f_name = #{f_name}")
	Employee getEmployee(@Param("f_name") String f_name);
	
	@Insert("INSERT INTO employee (employee_id, address, email, bank_account, sort_code, starting_salary, f_name, l_name, salary, nin)"
			+ "VALUES(#{employee_id}, #{address}, #{email}, #{bank_account}, #{sort_code}, #{starting_salary}, #{f_name}, #{l_name}, #{salary}, #{nin})")
	void insertEmployee(Employee employee);
}
