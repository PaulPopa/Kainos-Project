package app;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.security.*;

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
			
			Login login = new Login(mapper, sc);
			login.verifyLogin();
			
			System.out.println("Please input your new employee id\n"); 
			String emp_id = sc.nextLine();
			System.out.println("Please input the address\n"); 
			String address = sc.nextLine();
			System.out.println("Please input the email\n"); 
			String email = sc.nextLine();
			System.out.println("Please input the bank account\n"); 
			String bank_account = sc.nextLine();
			System.out.println("Please input the sort code\n"); 
			String sort_code = sc.nextLine();
			System.out.println("Please input the starting salary\n"); 
			double start_sal = Double.parseDouble(sc.nextLine());
			System.out.println("Please input the first name\n"); 
			String f_name = sc.nextLine();
			System.out.println("Please input the last name\n"); 
			String l_name = sc.nextLine();
			System.out.println("Please input the current salary\n"); 
			double salary = Double.parseDouble(sc.nextLine());
			System.out.println("Please input the National Insurance Number\n"); 
			String nin = sc.nextLine();
			System.out.println("Is this the employee you want to insert? Yes/No \n"); 
			System.out.println(emp_id + " " + address + " " + email + " " + bank_account + " " + sort_code + " " + start_sal + " " + f_name + " " + l_name + " " + salary + " " + nin);
			String yes = sc.nextLine();
			if (yes == "yes") {
			Employee e = new Employee(emp_id, address, email, bank_account, sort_code, start_sal, f_name, l_name, salary, nin);
			mapper.insertEmployee(e);
			System.out.println(e.getF_name());
			session.commit();
			System.out.println("Employee added");
			}

		} finally {
			session.close();
		}
	}
}
