package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static boolean checkIfEmployeeExists(List<Employee> employeesList, int id) {
        return employeesList.stream().anyMatch(employee -> employee.getId() == id);
    }

    public static Employee searchEmployee(List<Employee> employeesList, int id) {
        return employeesList.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        List<Employee> employeesList = new ArrayList<>();

        System.out.print("How many employees will be registered? ");
        int opt = scn.nextInt();

        for (int i = 0; i < opt; i++) {
            System.out.println("\nEmployee #" + (i + 1) + ":");
            System.out.print("Id: ");
            int id = scn.nextInt();

            while (checkIfEmployeeExists(employeesList, id)) {
                System.out.print("Id already taken! Try again: ");
                id = scn.nextInt();
            }

            System.out.print("Name: ");
            scn.nextLine();
            String name = scn.nextLine();

            System.out.print("Salary: ");
            double salary = scn.nextDouble();

            employeesList.add(new Employee(id, name, salary));
        }

        System.out.print("\nEnter the employee id that will have salary increase: ");
        int id = scn.nextInt();

        Employee foundEmployee = searchEmployee(employeesList, id);

        if (foundEmployee == null) {
            System.out.println("This id does not exist!");
        } else {
            System.out.print("Enter the percentage: ");
            double percentage = scn.nextDouble();

            foundEmployee.increaseSalary(percentage);
        }
        System.out.println("\nList of employees:");

        for (Employee employee : employeesList) {
            System.out.print(employee + "\n");
        }
    }
}
