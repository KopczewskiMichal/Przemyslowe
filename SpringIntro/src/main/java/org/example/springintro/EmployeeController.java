package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeManagementService employeeManagementService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeManagementService employeeManagementService, EmployeeService employeeService) {
        this.employeeManagementService = employeeManagementService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Person> getAllEmployees() {
        return employeeManagementService.getEmployees();
    }

    @PostMapping
    public String addEmployee(@RequestBody Person newEmployee) {
        employeeManagementService.addEmployee(newEmployee);
        return "Employee added successfully";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Person updatedEmployee) {
        boolean isUpdated = employeeManagementService.updateEmployee(id, updatedEmployee);
        return isUpdated ? "Employee updated successfully" : "Employee not found";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        boolean isDeleted = employeeManagementService.deleteEmployee(id);
        return isDeleted ? "Employee deleted successfully" : "Employee not found";
    }

    @GetMapping("/filter")
    public List<Person> filterEmployeesByCompany(@RequestParam String company) {
        return employeeManagementService.filterByCompany(company);
    }

    @GetMapping("/sort")
    public List<Person> sortEmployeesByLastName() {
        return employeeManagementService.sortByLastName();
    }

    @GetMapping("/key-employees")
    public Person[] getKeyEmployees() {
        return employeeService.getKeyEmployees();
    }

    @GetMapping("/employee/president")
    public Person getPresident() {
        return employeeService.getKeyEmployees()[0];
    }

    @GetMapping("/employee/vice-president")
    public Person getVicePresident() {
        return employeeService.getKeyEmployees()[1];
    }

    @GetMapping("/employee/secretary")
    public Person getSecretary() {
        return employeeService.getKeyEmployees()[2];
    }
}
