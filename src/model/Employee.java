package model;

public class Employee {
	private String employee_id;
	private String address;
	private String email;
	private String bank_account;
	private String sort_code;
	private double starting_salary;
	private String f_name;
	private String l_name;
	private double salary;
	private String nin;
	
	public Employee() {
		
	}
	
	public Employee(String employee_id, String f_name, String l_name) {
		this();
		this.employee_id = employee_id;
		this.f_name = f_name;
		this.l_name = l_name;
	}
	
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getNin() {
		return nin;
	}
	public void setNin(String nin) {
		this.nin = nin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getSort_code() {
		return sort_code;
	}
	public void setSort_code(String sort_code) {
		this.sort_code = sort_code;
	}
	public double getStarting_salary() {
		return starting_salary;
	}
	public void setStarting_salary(double starting_salary) {
		this.starting_salary = starting_salary;
	}
	
	
	
}
