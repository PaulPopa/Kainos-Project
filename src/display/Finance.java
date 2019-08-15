package display;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import data.EmployeesMapper;
import model.Employee;
import model.SalesEmployee;

public class Finance {
	private static final double TAX_RATE = 0.25;
	private EmployeesMapper mapper;
	private Scanner sc;
	private SqlSession session;
	
	public Finance(EmployeesMapper mapper, Scanner sc, SqlSession session) {
		this.mapper = mapper;
		this.sc = sc;
		this.session = session;
	}
	
	public void getNetPayReport() {
		List<Employee> employees = mapper.getEmployees();
		List<SalesEmployee> salesEmployees = mapper.getSalesEmployees();
		for(Employee employee : employees){
			double salary = employee.getSalary();
			System.out.println(salary);
			double netSalary = (1 - TAX_RATE) * salary;
			System.out.println(employee.getF_name() + ": " + netSalary );
		}
		
		for(SalesEmployee salesEmployee : salesEmployees) {
			Employee employee = mapper.getEmployeeById(salesEmployee.getEmployee_id());
			double salary = employee.getSalary();
			double commissionRate = salesEmployee.getCommision_rate();
			double totalSales = salesEmployee.getSales_for_period();
			double netPay = salary + commissionRate * totalSales;
			netPay = (1 - TAX_RATE) * netPay;
			System.out.println(employee.getF_name() + ": " + netPay);
		}
	}
	
	public void selectEmployees() {
		
	}

}
