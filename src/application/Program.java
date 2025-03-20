package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entitites.Employee;

public class Program {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader (new FileReader (path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter the salary base: ");
			Double salaryBase = sc.nextDouble();
			
			List<String> filteredEmployees = list.stream()
	                    .filter(emp -> emp.getSalary() > salaryBase)
	                    .map(Employee::getEmail)
	                    .sorted()
	                    .collect(Collectors.toList());
		
			System.out.println("Emails of employees earning more than " + salaryBase + ":");
			filteredEmployees.forEach(System.out::println);
			
			double totalSalaryM = list.stream()
				    .filter(emp -> emp.getName().charAt(0) == 'M') 
				    .mapToDouble(Employee::getSalary)
				    .sum(); 
            
			System.out.println("Total salary of employees starting with the letter M  " + salaryBase + ":");
			System.out.println(totalSalaryM);
			
			
		
		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
		}
		sc.close();
		
		
		
	}
		
	
}
