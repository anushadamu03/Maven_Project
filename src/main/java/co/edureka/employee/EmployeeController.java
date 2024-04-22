package co.edureka.employee;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edureka.ems.dao.employeeDAO;
import co.edureka.ems.dto.Employee;

public class EmployeeController {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("emp-orm-beans.xml");
		
		employeeDAO dao=(employeeDAO)context.getBean("empdao");
		
		
		while(true) {
			System.out.println("1. New Employee");
			System.out.println("2. Find Employee ");
			
			System.out.print("\nenter an option: ");
			int opt = sc.nextInt();
			
			Employee emp;
			int empid;
			
			switch(opt) {
			 case 1:
				emp= getEmployee();
				dao.saveEmployee(emp);
				break;
		
			 case 2:
				System.out.print("Enter the employee number ");
				empid = sc.nextInt();
					
				emp = (Employee)dao.getById(empid);
				if(emp != null) {
					System.out.println(emp);
					double netsalary = calculateNetSalary(emp);
					System.out.println("netsalary:");
					System.out.println(netsalary);
						
				}else {
					System.err.println("@@@--- NO MATCHING EMPLOYEE FOUND ---@@@");
				}
					
				 
				 
			}
		}
	}
		private static Employee getEmployee() {
			System.out.print("enter employee id: ");
			int eid = sc.nextInt();
			sc.nextLine();
			
			System.out.print("enter employee name: ");
			String name = sc.nextLine();
			sc.nextLine();
			
			System.out.print("enter  basic: ");
			int  basic = sc.nextInt();
			sc.nextLine();
			
			System.out.println("enter hra:");
			int  hra =sc.nextInt();
			sc.nextLine();
			
			System.out.println("enter da:");
			int  da =sc.nextInt();
			sc.nextLine();
			
			System.out.println("enter deductions:");
			int ded =sc.nextInt();
			sc.nextLine();
			
			
			return new Employee(eid, name,basic,hra,da,ded);
			
		}
		
		public static double calculateNetSalary(Employee employee) {
	        double grossSalary = employee.getBasic() + employee.getHra() + employee.getDa();
	        double tax = 0.15 * grossSalary;
	        double netSalary = grossSalary - (tax + employee.getDeductions());
	        return netSalary;
	    }
}