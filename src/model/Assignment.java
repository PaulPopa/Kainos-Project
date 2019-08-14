package model;

import java.sql.Date;

public class Assignment {
	private String employeeId;
	private int projectId;
	private Date startDate;
	private Date endDate;
	
	public Assignment() {}
	
	public Assignment(String employeeId, int projectId, Date startDate, Date endDate) {
		setEmployeeId(employeeId);
		setProjectId(projectId);
		setStartDate(startDate);
		setEndDate(endDate);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
