package app;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import data.EmployeesMapper;
import model.Employee;

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
			System.out.println("Welcome to the Diamond Database! Please input your query"); 
			String emp_id = sc.nextLine();
			String address = sc.nextLine();
			String email = sc.nextLine();
			String bank_account = sc.nextLine();
			String sort_code = sc.nextLine();
			double start_sal = Double.parseDouble(sc.nextLine());
			String f_name = sc.nextLine();
			String l_name = sc.nextLine();
			double salary = Double.parseDouble(sc.nextLine());
			String nin = sc.nextLine();
			Employee e = new Employee(emp_id, address, email, bank_account, sort_code, start_sal, f_name, l_name, salary, nin);
			mapper.insertEmployee(e);
			System.out.println(e.getF_name());
			session.commit();

		} finally {
			session.close();
		}
	}
}
