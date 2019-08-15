package login;

import java.util.Scanner;

import data.EmployeesMapper;
import model.User;

public class Login {
	
	private EmployeesMapper mapper;
	private Scanner sc;

	public Login(EmployeesMapper mapper, Scanner sc) {
		this.mapper = mapper;
		this.sc = sc;
	}
	
	public void verifyLogin() {
		System.out.println("Enter your login credentials:");
		String username = sc.nextLine();
		System.out.println("Enter your password:");
		String password = sc.nextLine();
	    String hashPassword = User.hashPassword(password);
	    System.out.println(hashPassword);
	    
	    User user = mapper.getUser(username);
	    System.out.println(user);
	    boolean incorrectCredentials = true;
	    
	    while (incorrectCredentials == true) { 	
			while (user == null) {
				System.err.println("No user with that username!");
				System.out.println("Enter your username for finance team:");
				username = sc.nextLine();
				user = mapper.getUser(username);
			} 
			if (!hashPassword.contentEquals(user.getPassword())) {
				System.err.println("Incorrect credentials");
				System.out.println("Enter your password:");
				password = sc.nextLine();
				hashPassword = User.hashPassword(password);
			} else {
				incorrectCredentials = false;
				System.out.println("Logged in successfully");
			}
		}
	}
}
