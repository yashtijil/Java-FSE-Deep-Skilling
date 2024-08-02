import java.util.Arrays;

public class EmployeeManagement {
    private Employee[] employees;
    private int count;

    public EmployeeManagement(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count++] = employee;
        } else {
            System.out.println("Cannot add more employees.");
        }
    }

    public Employee searchEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee != null && employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee);
            }
        }
    }

    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                employees[i] = employees[count - 1]; 
                employees[count - 1] = null; 
                count--;
                return;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
    }

    public static void main(String[] args) {
        EmployeeManagement system = new EmployeeManagement(5);

        system.addEmployee(new Employee(1, "Peter Parker", "Manager", 75000));
        system.addEmployee(new Employee(2, "Tony Stark", "Developer", 60000));
        system.addEmployee(new Employee(3, "Chris Evans", "Analyst", 55000));

        System.out.println("Employees in the system:");
        system.traverseEmployees();

        System.out.println("\nSearching for employee with ID 2:");
        Employee emp = system.searchEmployee(2);
        System.out.println(emp != null ? emp : "Employee not found.");

        System.out.println("\nDeleting employee with ID 2:");
        system.deleteEmployee(2);

        System.out.println("\nEmployees after deletion:");
        system.traverseEmployees();
    }
}
