package display;

import java.util.List;
import java.util.Scanner;

import data.EmployeesMapper;
import model.Department;
import model.Employee;
import model.SalesEmployee;

public class HR {
	private EmployeesMapper mapper;
	private Scanner sc;
	
	public HR(EmployeesMapper mapper, Scanner sc) {
		this.mapper = mapper;
		this.sc = sc;
	}
	public void getDepEmployee() {
		String d_name = sc.nextLine();
		List<Employee> employees = mapper.getDepartmentEmployees(d_name);
		for (Employee emp : employees) {
			System.out.println(emp.getF_name());
		}
	}
	public void createSales() {
		String emp_id = enterID(sc);
		double commision_rate = enterCommisionRate(sc);
		double sales_for_period = enterSalesForPeriod(sc);
		
		SalesEmployee e = new SalesEmployee(emp_id, commision_rate, sales_for_period);
		mapper.insertSalesEmployee(e);	
	}
	
	public void createEmployee() {
		
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
		
		Department department = enterDept(sc);
		
		convertEmptyToNull(bank_account, sort_code, start_sal, salary, nin);
		
		System.out.println("Adding this employee to database: \n"); 

		System.out.println(emp_id + " " + address + " " + email + " " + bank_account + " " + sort_code + " " + start_sal + " " + f_name + " " + l_name + " " + salary + " " + nin);
		//String yes = sc.nextLine();
		//if (yes == "yes") {
		Employee e = new Employee(emp_id, address, email, bank_account, sort_code, start_sal, f_name, l_name, salary, nin);
		mapper.insertEmployee(e);
		//}
	}
	private static Department enterDept(Scanner sc) {
		// TODO Auto-generated method stub
		return null;
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
	
	private static double enterSalesForPeriod(Scanner sc) {
		System.out.println("Please input the employees total sales for this period\n"); 
		double sfp = Double.parseDouble(sc.nextLine()); 
		return sfp;
	}
	
	private static double enterCommisionRate(Scanner sc) {
		double com;
		boolean con = false;
		System.out.println("Please input your employee's Commision rate\n"); 
		do {
			if (con) System.out.println("Invalid input. Please enter commision rate again\n"); 
			com = Double.parseDouble(sc.nextLine()); 
			con = true;
		} while (com < 1 && com > 0);
		return com;
	}
	
	private static String enterNin(Scanner sc) {
		String nin;
		boolean con = false;
		System.out.println("Please input your employee's National Insurance Number (can be skipped and added later\n"); 
		do {
			if (con) System.out.println("Invalid input. Please input National Insurance Number again (must be correctly formated)\n"); 
			nin = sc.nextLine(); 
			con = true;
		} while (!nin.matches("^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$"));
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
		} while (name.matches(".*[0-9]+.*") || name.length() > 50);
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
		} while (sortcode.length() != 6 || sortcode.matches(".*[a-zA-Z]+.*"));
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
		} while (addr.length() > 50);
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
}