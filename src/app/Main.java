package app;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import data.EmployeesMapper;
import display.TalentManager;
import display.HR;
import login.Login;
import model.Department;
import model.Employee;
import model.User;

public class Main {

	public static void main(String args[]) {

		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("data/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);
		SqlSession session = factory.openSession();

		try {
			EmployeesMapper mapper = session.getMapper(EmployeesMapper.class);
			Scanner sc = new Scanner(System.in);
			TalentManager tm = new TalentManager(mapper, sc, session);
			HR hr = new HR(mapper, sc);
			
			Boolean validInput = true;
			String department;
			do {
				System.out.println("Welcome to the Diamond Database\n" + "What department are you in?" + " Please enter, \"HR\", \"Finance\", \"Sales\", \"Talent\"");
				department = sc.nextLine();
				switch (department) {
				case "HR":
					System.out.println("What would you like to do?\n" 
				            + "1.Create new Employee\n"
							+ "2.Create new SalesEmployee\n" 
							+ "3.Get Employees for Department\n"
							+ "Enter 1, 2 or 3\n");
					String hr_choice = sc.nextLine();
					switch (hr_choice) {
					case "1": 
						hr.createEmployee();
						session.commit();
					    System.out.println("Employee added");
												break;
					case "2":
						hr.createSales();
						session.commit();
						System.out.println("Sales Employee added");	
						break;
					case "3":
						hr.getDepEmployee();
						break;
					default:
						System.out.println("Choice not recognised, please enter numbers 1, 2 or 3");
					}
					break;
				case "Finance":
					break;
				case "Sales":
					break;
				case "Talent": 
					boolean exit = false;
					while(!exit) {
						System.out.println("What would you like to do?\n"
								+"1.Create projects\n"
								+"2.Assign employee to a project\n"
								+"3.Select all employees on a particular project\n"
								+"4.Select all projects with no employees assigned\n"
								+"5.Select all employees with no assigned projects\n"
								+"6.Select the number of employees per specified project\n"
								+ "Enter 1, 2, 3, 4, 5, 6 or exit\n");
						String talent_choice = sc.nextLine();
						switch(talent_choice) {
						  case "1": tm.createProjects();
						    break; 
						  case "2": tm.assignEmployeeToProject();
						    break;
						  case "3": tm.selectEmployeesForProject();
						    break;
						  case "4": tm.selectProjectsWithNoEmployees();
						    break;
						  case "5": tm.selectEmployeesForProject();
						    break;
						  case "6": tm.selectNumberOfEmployeesPerProject();
						    break;
						  case "exit":
							  exit = true;
							  break;
						} 
					}
				default:
					System.out.println(
							"Department not recognised, please enter, \"HR\", \"Finance\", \"Sales\", \"Talent\"");
					validInput = false;
					break;
				}
			}while (!validInput &&  !department.equals("exit") );

		}finally {

		session.close();}

}}
