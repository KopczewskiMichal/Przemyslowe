package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class EmployeeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @RestController
    public class EmployeeController {

        private final EmployeeService employeeService;
        private final CsvReaderService csvReaderService;
        private final EmployeeManagementService employeeManagementService;
        private List<Person> employees;

        @Autowired
        public EmployeeController(EmployeeService employeeService, CsvReaderService csvReaderService, EmployeeManagementService employeeManagementService) {
            this.employeeService = employeeService;
            this.csvReaderService = csvReaderService;
            this.employeeManagementService = employeeManagementService;
            this.employees = csvReaderService.readEmployeesFromCsv("src/main/resources/MOCK_DATA.csv"); 
        }

        @GetMapping("/key-employees")
        public String displayKeyEmployees() {
            return employeeService.displayKeyEmployees();
        }

        @GetMapping("/employees")
        public String getAllEmployees() {
            if (employees == null) {
                return "Lista pracowników nie została jeszcze zainicjalizowana.";
            }
            StringBuilder sb = new StringBuilder();
            employees.forEach(employee -> sb.append(employee.toString()).append("\n"));
            return sb.toString();
        }

        @GetMapping("/employees/by-country")
        public String getEmployeesByCountry(@RequestParam String country) {
            if (employees == null) {
                return "Lista pracowników nie została jeszcze zainicjalizowana.";
            }
            List<Person> filteredEmployees = employeeManagementService.filterByCompany(employees, country);
            StringBuilder sb = new StringBuilder();
            filteredEmployees.forEach(employee -> sb.append(employee.toString()).append("\n"));
            return sb.toString();
        }

        @GetMapping("/employees/sorted-by-lastname")
        public String getEmployeesSortedByLastName() {
            if (employees == null) {
                return "Lista pracowników nie została jeszcze zainicjalizowana.";
            }
            List<Person> sortedEmployees = employeeManagementService.sortByLastName(employees);
            StringBuilder sb = new StringBuilder();
            sortedEmployees.forEach(employee -> sb.append(employee.toString()).append("\n"));
            return sb.toString();
        }
    }
}
