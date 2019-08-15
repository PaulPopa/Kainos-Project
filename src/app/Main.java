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
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("Welcome to the Diamond Database! Please input your query"
		 * ); String input = sc.nextLine();
		 * 
		 * switch(input) { case "hello": System.out.println("Hi there"); break; default:
		 * System.out.println("Input not recognised"); }
		 */
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
			System.out.println("hbye");
			EmployeesMapper mapper = session.getMapper(EmployeesMapper.class);
			System.out.println("hello");
			Employee john = mapper.getEmployee("Dave");
			System.out.println(john.getF_name());

		} finally {
			session.close();
		}
 	}
}
