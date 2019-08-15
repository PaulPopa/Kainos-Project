package display;

import java.util.Scanner;

import data.EmployeesMapper;
import model.SalesEmployee;

public class Sales {
	private EmployeesMapper mapper;
	private Scanner sc;

	public Sales(EmployeesMapper mapper, Scanner sc) {
		this.mapper = mapper;
		this.sc = sc;
	}

}
