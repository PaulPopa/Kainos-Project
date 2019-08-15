package model;

public class Department {
	private String department_id;
	private String d_name;
	
	public Department() {
		
	}
	
	public Department(String department_id, String d_name)  {
		this();
		this.setDepartment_id(department_id);
		this.setD_name(d_name);
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	
}
