package model;

public class SalesEmployee extends Employee{
	private String employee_id;
	private double commision_rate;
	private double sales_for_period;
	
	public SalesEmployee() {
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public double getCommision_rate() {
		return commision_rate;
	}

	public void setCommision_rate(double commision_rate) {
		this.commision_rate = commision_rate;
	}

	public double getSales_for_period() {
		return sales_for_period;
	}

	public void setSales_for_period(double sales_for_period) {
		this.sales_for_period = sales_for_period;
	}
}
