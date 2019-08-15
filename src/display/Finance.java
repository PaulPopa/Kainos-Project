package display;

import java.util.Scanner;

import data.EmployeesMapper;

public class Finance {
	private EmployeesMapper mapper;
	private Scanner sc;

	public Finance(EmployeesMapper mapper, Scanner sc) {
		this.mapper = mapper;
		this.sc = sc;
	}
}
