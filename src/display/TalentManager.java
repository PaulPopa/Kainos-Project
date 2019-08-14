package display;

import java.sql.Date;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import data.EmployeesMapper;
import model.Assignment;
import model.Project;

public class TalentManager {
	
	private EmployeesMapper mapper;
	private Scanner sc;
	private SqlSession session;

	public TalentManager(EmployeesMapper mapper, Scanner sc, SqlSession session) {
		this.mapper = mapper;
		this.sc = sc;
		this.session = session;
	}
	
	public void selectEmployeesForProject() {
		System.out.println("Upon entering a project id, all the employees working on it will be displayed");
		int projectId = enterProjectId(sc);
		
		String employees = mapper.getEmployeesInProject(projectId);
		System.out.println("Here are the employees working on project: " + projectId + "\n" + employees);
	}
	
	public void assignEmployeeToProject() {
		String employeeId = enterEmployeeId(sc);
		int projectId = enterProjectId(sc);
		Date startDate = enterDate(sc, "starting date of the project\n");
		Date endDate = enterDate(sc, "ending date of the project\n");
		
		System.out.println("Assinging this employee to this project: \n");
		Assignment assignment = new Assignment(employeeId, projectId, startDate, endDate);
		mapper.insertAssignment(assignment);
		System.out.println("Employee assigned");
		
		session.commit();
	}
	
	public void createProjects() {
		int projectId = enterProjectId(sc);
		String projectName = enterProjectName(sc);
		String leaderId = enterEmployeeId(sc);		
		
		System.out.println("Adding this project to the database: \n");
		Project project = new Project(projectId, projectName, leaderId);
		mapper.insertProject(project);
		System.out.println("Project created");
		
		session.commit();
	}
	
	public int enterProjectId(Scanner sc) {
		System.out.println("Enter the project id\n");
		String projectId = "";
		boolean con = false;
		do {
			if (con) System.out.println("Invalid input. The id must be a number\n");
			
			projectId = sc.nextLine();
			con = true;
		} while (projectId.matches(".*[a-zA-Z]+.*"));
		return Integer.parseInt(projectId);
	}
	
	public String enterProjectName(Scanner sc) {
		String projectName = "";
		boolean con = false;
		System.out.println("Please input the project name\n");
		do {
			if (con) System.out.println("Invalid input. Please input a project name with less than 32 characters\n");
			
			projectName = sc.nextLine();
			con = true;
			
		} while (projectName.contentEquals("") || projectName.length() > 32);
		return projectName;
	}
	
	public String enterEmployeeId(Scanner sc) {
		String leaderId = null;
		boolean con = false;
		System.out.println("Please input the leader id\n");
		do {
			if (con) System.out.println("Invalid input. Please input a leader id that is available\n");
			
			leaderId = sc.nextLine();
			con = true;
			
		} while (leaderId.length() < 8);
		return leaderId;
	}
	
	public Date enterDate(Scanner sc, String message) {
		String date = null;
		boolean con = false;
		System.out.println("Please input the " + message);
		do {
			if (con) System.out.println("Invalid input. Please enter a date again");
			
			date = sc.nextLine();
			con = true;
		} while (!date.matches("^\\d{4}-\\d{2}-\\d{2}$"));
		return Date.valueOf(date);
	}
}
