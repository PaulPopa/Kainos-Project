package app;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import data.EmployeesMapper;
import login.Login;
import model.Employee;
import model.User;

public class Main {

	public Main() {
	}

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
			
			//Login login = new Login(mapper, sc);
			//login.verifyLogin();
			
			String emp_id = enterID(sc);
			
			String address = enterAddress(sc);
			
			String email = enterEmail(sc);
			
			String bank_account = enterBankAcc(sc);
			
			String sort_code = enterSortCode(sc);

			double start_sal = enterStartSal(sc);

			String f_name = enterName(sc, "f");
			
			String l_name = enterName(sc, "l");
			
			double salary = enterSalary(sc);
			
			String nin = enterNin(sc);
			
			System.out.println("Adding this employee to database: \n"); 

			System.out.println(emp_id + " " + address + " " + email + " " + bank_account + " " + sort_code + " " + start_sal + " " + f_name + " " + l_name + " " + salary + " " + nin);
			//String yes = sc.nextLine();
			//if (yes == "yes") {
			Employee e = new Employee(emp_id, address, email, bank_account, sort_code, start_sal, f_name, l_name, salary, nin);
			mapper.insertEmployee(e);
			session.commit();
			System.out.println("Employee added");
			//}

		} finally {
			session.close();
		}
	}
	private static String enterNin(Scanner sc) {
		String nin;
		do {
			System.out.println("Please input their National Insurance Number\n"); 
			nin = sc.nextLine(); 
		} while (!nin.matches("^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$"));
		return nin;
	}

	private static double enterSalary(Scanner sc) {
		String sal;
		do {
			System.out.println("Please input their current salary\n"); 
			sal = sc.nextLine(); 
		} while (sal.matches(".*[a-zA-Z]+.*"));
		return Double.parseDouble(sal);
	}

	private static String enterName(Scanner sc, String type) {
		String name;
		do {
			if(type == "f")
			System.out.println("Please input their first name (50 character limit)\n"); 
			else System.out.println("Please input their last name (50 character limit)\n"); 
			name = sc.nextLine(); 
		} while (!name.matches(".*[a-zA-Z]+.*") && name.length() > 50);
		return name;
	}

	private static double enterStartSal(Scanner sc) {
		String startsal;
		do {
			System.out.println("Please input their initial starting salary\n"); 
			startsal = sc.nextLine(); 
		} while (startsal.matches(".*[a-zA-Z]+.*"));
		return Double.parseDouble(startsal);
	}

	private static String enterSortCode(Scanner sc) {
		String sortcode;
		do {
			System.out.println("Please input their sort code (6 digits)\n"); 
			sortcode = sc.nextLine(); 
			sortcode = sortcode.replace("-", "");
		} while (sortcode.length() != 6 && sortcode.matches(".*[a-zA-Z]+.*"));
		return sortcode;
	}

	private static String enterBankAcc(Scanner sc) {
		String bankacc;
		do {
			System.out.println("Please input their bank account details (8 digits) \n"); 
			bankacc = sc.nextLine(); 
		} while (bankacc.length() != 8 && !bankacc.contains("[a-zA-Z]+"));
		return bankacc;
	}

	private static String enterEmail(Scanner sc) {
		String email;
		do {
			System.out.println("Please input their email address (must be less than 50 characters)\n"); 
			email = sc.nextLine(); 
		} while (!isValid(email));
		return email;
	}

	private static String enterAddress(Scanner sc) {
		String addr;
		do {
			System.out.println("Please input their living address (must be less than 50 characters)\n"); 
			addr = sc.nextLine(); 
		} while (addr.length() > 50);
		return addr;
	}

	private static String enterID(Scanner sc) {
		String emp_id;
		do {
			System.out.println("Please input your new employee id (must be 8 characters)\n"); 
			emp_id = sc.nextLine(); 
		} while (emp_id.length() != 8);
		return emp_id;
		
	}
	
	   static boolean isValid(String email) {
		      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		      return email.matches(regex);
		   }
}
