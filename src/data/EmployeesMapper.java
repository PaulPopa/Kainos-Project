package data;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.Employee;

public interface EmployeesMapper {
	@Select("SELECT employee_id, f_name, l_name FROM employee WHERE f_name = 'Dave'")
	Employee getEmployee(@Param("f_name)") String f_name);
}
