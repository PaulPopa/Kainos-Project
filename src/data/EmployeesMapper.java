package data;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.Assignment;
import model.Department;
import model.Employee;
import model.Project;
import model.SalesEmployee;
import model.User;

public interface EmployeesMapper {
	@Select("SELECT employee_id, f_name, l_name FROM employee WHERE f_name = #{f_name}")
	Employee getEmployee(@Param("f_name") String f_name);
	
	@Select("SELECT f_name, l_name FROM employee JOIN department using(department_id) WHERE d_name = #{d_name}")
	List<Employee> getDepartmentEmployees(@Param("d_name") String d_name);
	
	@Insert("INSERT INTO employee (employee_id, address, email, bank_account, sort_code, starting_salary, f_name, l_name, salary, nin, department_id)"
			+ "VALUES(#{employee_id}, #{address}, #{email}, #{bank_account}, #{sort_code}, #{starting_salary}, #{f_name}, #{l_name}, #{salary}, #{nin}, #{department_id})")
	void insertEmployee(Employee employee);
	
	@Insert("INSERT INTO salesEmployee (employee_id, commision_rate, sales_for_period)"
			+ "VALUES(#{employee_id}, #{commision_rate}, #{sales_for_period}")
	void insertSalesEmployee(SalesEmployee sales_employee);
	
	@Select("SELECT username, password FROM user WHERE username = #{username}")
	User getUser(@Param("username") String username);
	
	@Insert("INSERT INTO project VALUES (#{projectId}, #{projectName}, #{leaderId})")
	void insertProject(Project project);
	
	@Insert("INSERT INTO assignment VALUES(#{employeeId}, #{projectId}, #{startDate}, #{endDate})")
	void insertAssignment(Assignment assignment);
	
    @Select("SELECT GROUP_CONCAT(CONCAT_WS(' ', employee.f_name, employee.l_name) SEPARATOR ';') from project, employee, assignment where project.project_id = assignment.project_id and employee.employee_id = assignment.employee_id and project.project_id = #{project_id} group by project.project_id")
    String getEmployeesInProject(@Param("project_id") int projectId);
    
    @Select("SELECT project_id AS projectId, project_name AS projectName, leader_id as leaderId FROM project where project.project_id NOT IN (SELECT project_id from assignment)")
    List<Project> getProjectWithNoAsignees();
    
    @Select("SELECT * FROM employee where employee_id NOT IN (SELECT employee_id FROM assignment)")
    List<Employee> getEmployeesWithNoProjects();
    
    @Select("select COUNT(employee.employee_id) FROM project, employee, assignment where project.project_id = assignment.project_id and assignment.employee_id = employee.employee_id and project.project_id = #{project_id} GROUP BY project.project_id")
    int getNumberOfEmployeesOnProject(@Param("project_id") int projectId);

	@Select("SELECT * FROM employee WHERE employee_id NOT IN "
			+ "(SELECT employee_id FROM salesEmployee)")
	List<Employee> getEmployees();
	
	@Select("SELECT * FROM salesEmployee")
	List<SalesEmployee> getSalesEmployees();
	
	@Select("SELECT * WHERE employee_id = #{employee_id}")
	Employee getEmployeeById(@Param("employee_id") String f_name);
}
