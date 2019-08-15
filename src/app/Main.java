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
			
			//TalentManager tm = new TalentManager(mapper, sc, session);
			//tm.selectNumberOfEmployeesPerProject();
			//System.exit(1);
			
			
			//System.out.println("Input the name of the department that you would like to get the employees from.");
			
//			Login login = new Login(mapper, sc);
//			login.verifyLogin();
			

//			Login login = new Login(mapper, sc);
//			login.verifyLogin();
			/*
			String d_name = sc.nextLine();
			List<Employee> employees = mapper.getDepartmentEmployees(d_name);
			for(Employee emp : employees) {
				System.out.println(emp.getF_name());
			}*/
			
			//Login login = new Login(mapper, sc);
			//login.verifyLogin();
			System.out.println("Welcome to the Diamond Database for entering a new employee\n");
			
			String emp_id = enterID(sc);
			
			String f_name = enterName(sc, "f");
			
			String l_name = enterName(sc, "l");
			
			String address = enterAddress(sc);
			
			String email = enterEmail(sc);
			
			String bank_account = enterBankAcc(sc);
			
			String sort_code = enterSortCode(sc);

			double start_sal = enterStartSal(sc);

			double salary = enterSalary(sc);
			
			String nin = enterNin(sc);
			
			String department = enterDept(sc);
			
			convertEmptyToNull(bank_account, sort_code, start_sal, salary, nin);
			
			System.out.println("Adding this employee to database: \n"); 

			System.out.println(emp_id + " " + address + " " + email + " " + bank_account + " " + sort_code + " " + start_sal + " " + f_name + " " + l_name + " " + salary + " " + nin + " " + department);
			//String yes = sc.nextLine();
			//if (yes == "yes") {
			Employee e = new Employee(emp_id, address, email, bank_account, sort_code, start_sal, f_name, l_name, salary, nin, department);
			mapper.insertEmployee(e);
			session.commit();
			System.out.println("Employee added");
			
			/*
			String d_name = sc.nextLine();
			List<Employee> employees = mapper.getDepartmentEmployees(d_name);
			for(Employee emp : employees) {
				System.out.println(emp.getF_name());
			}*/
			//}

		} finally {
			session.close();
		}
	}
	private static String enterDept(Scanner sc) {
		System.out.println("Input the name of the department that you would like to get the employees from (sales | technology)");
		String deptstr;
		String deptId = "fakedept";
		boolean con = false;
		boolean deptExist = false;
		do {
			if (con) System.out.println("Please enter a valid department name");
			deptstr = sc.nextLine();
			switch(deptstr) 
			{
			case "sales": 
				deptId = "SALE";
				deptExist = true;
				break;
			case "technology": 
				deptId = "TECH";
				deptExist = true;
				break;
			} 
			con = true;
		} while(!deptExist);
		
		return deptId;
	}
	private static void convertEmptyToNull(String bank_account, String sort_code, Double start_sal, Double salary,
			String nin) {
		if(bank_account == "")
			bank_account = null;
		if(sort_code == "")
			sort_code = null;
		if(start_sal == 0)
			start_sal = null;
		if(salary == 0)
			salary = null;
		if(nin == "")
			nin = null;
		
	}
	private static String enterNin(Scanner sc) {
		String nin;
		boolean con = false;
		System.out.println("Please input your employee's National Insurance Number (can be skipped and added later)\n"); 
		do {
			if (con) System.out.println("Invalid input. Please input National Insurance Number again (must be correctly formated)\n"); 
			nin = sc.nextLine(); 
			con = true;
		} while (!nin.matches("^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$") && nin.length() != 0);
		return nin;
	}

	private static double enterSalary(Scanner sc) {
		String sal;
		boolean con = false;
		System.out.println("Please input your employee's current salary (can be skipped and added later)\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter current salary again\n"); 
			sal = sc.nextLine(); 
			con = true;
			if (sal.isEmpty())
					sal = "0";
		} while (sal.matches(".*[a-zA-Z]+.*") || Double.parseDouble(sal) < 0);
		return Double.parseDouble(sal);
	}

	private static String enterName(Scanner sc, String type) {
		String name;
		boolean con = false;
		if(type == "f") 
			System.out.println("Please input your employee's first name");
		else System.out.println("Please input your employee's last name");
		do {
			if (con && type =="f") System.out.println("Invalid input. Please enter first name again (50 character limit)");
			else {
				if (con) System.out.println("Invalid input. Please enter last name again (50 character limit)\n"); 
			}
			name = sc.nextLine(); 
			con = true;
		} while (name.matches(".*[0-9]+.*") || (name.length() > 50) || (name.isEmpty()));
		return name;
	}

	private static double enterStartSal(Scanner sc) {
		String startsal;
		boolean con = false;
		System.out.println("Please input your employee's initial starting salary (can be skipped and added later)\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter starting salary again\n"); 
			startsal = sc.nextLine(); 
			con = true;
			if (startsal.isEmpty())
				startsal = "0";
		} while (startsal.matches(".*[a-zA-Z]+.*") || Double.parseDouble(startsal) < 0);
		return Double.parseDouble(startsal);
	}

	private static String enterSortCode(Scanner sc) {
		String sortcode;
		boolean con = false;
		System.out.println("Please input your employee's sort code (6 digits - can be skipped and added later)\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter sort code again (must be 6 digits)\n"); 
			sortcode = sc.nextLine(); 
			sortcode = sortcode.replace("-", "");
			con = true;
		} while ((sortcode.length() != 6 || sortcode.length() != 0) && sortcode.matches(".*[a-zA-Z]+.*"));
		return sortcode;
	}

	private static String enterBankAcc(Scanner sc) {
		String bankacc;
		boolean con = false;
		System.out.println("Please input your employee's bank account (8 digits - can be skipped and added later)\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter bank account again (must be 8 digits)\n"); 
			bankacc = sc.nextLine();
			con = true;
		} while ((bankacc.length() != 8 || bankacc.length() != 0) && bankacc.matches(".*[a-zA-Z]+.*"));
		return bankacc;
	}
//
	private static String enterEmail(Scanner sc) {
		String email;
		boolean con = false;
		System.out.println("Please input your employee's email address\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter email address again (must be less than 50 characters)\n"); 
			email = sc.nextLine(); 
			con = true;
		} while (!isValid(email));
		return email;
	}

	private static String enterAddress(Scanner sc) {
		String addr;
		boolean con = false;
		System.out.println("Please input your employee's address\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter address again (must be less than 50 characters)\n"); 
			addr = sc.nextLine(); 
			con = true;
		} while (addr.length() > 50 || addr.isEmpty());
		return addr;
	}

	private static String enterID(Scanner sc) {
		String emp_id;
		boolean con = false;
		System.out.println("Please input your new employee's id (8 characters)\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter employee id again (must be 8 characters)\n"); 
			emp_id = sc.nextLine(); 
			con = true;
		} while (emp_id.length() != 8);
		return emp_id;
		
	}
	
	   static boolean isValid(String email) {
		      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		      return email.matches(regex);
		   }
}
