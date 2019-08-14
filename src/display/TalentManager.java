package display;

import java.util.Scanner;

import data.EmployeesMapper;
import model.Project;

public class TalentManager {
	
	private EmployeesMapper mapper;
	private Scanner sc;

	public TalentManager(EmployeesMapper mapper, Scanner sc) {
		this.mapper = mapper;
		this.sc = sc;
	}
	
	public void CreateProjects() {
		int projectId = enterProjectId(sc);
		String projectName = enterProjectName(sc);
		String leaderId = enterLeaderId(sc);		
		
		System.out.println("Adding this project to the database: ");
		Project project = new Project(projectId, projectName, leaderId);
		mapper.insertProject(project);
		System.out.println("Project created");
	}
	
	public int enterProjectId(Scanner sc) {
		System.out.println("Enter the project id\n");
		int projectId = 0;
		boolean con = false;
		do {
			if (con) System.out.println("Invalid input. The id cannot be negative or zero\n");
			
			projectId = sc.nextInt();
			con = true;
		} while (projectId <= 0);
		return projectId;
	}
	
	public String enterProjectName(Scanner sc) {
		String projectName = null;
		boolean con = false;
		System.out.println("Please input the project name");
		do {
			if (con) System.out.println("Invalid input. Please input a project name with less than 32 characters\n");
			
			projectName = sc.nextLine();
			con = true;
			
		} while (projectName.length() > 32);
		return projectName;
	}
	
	public String enterLeaderId(Scanner sc) {
		String leaderId = null;
		boolean con = false;
		System.out.println("Please input the leader id");
		do {
			if (con) System.out.println("Invalid input. Please input a leader id that is available\n");
			
			leaderId = sc.nextLine();
			con = true;
			
		} while (leaderId.length() < 8);
		return leaderId;
	}
}
