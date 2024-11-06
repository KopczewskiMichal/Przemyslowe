package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Endpoint do pobrania najważniejszych pracowników w formacie JSON
    @GetMapping("/key-employees")
    public Person[] getKeyEmployees() {
        return employeeService.getKeyEmployees();
    }

    // Endpoint do pobrania prezydenta w formacie JSON
    @GetMapping("/employee/president")
    public Person getPresident() {
        return employeeService.getKeyEmployees()[0];  // Zwróć prezydenta
    }

    // Endpoint do pobrania wiceprezydenta w formacie JSON
    @GetMapping("/employee/vice-president")
    public Person getVicePresident() {
        return employeeService.getKeyEmployees()[1];  // Zwróć wiceprezydenta
    }

    // Endpoint do pobrania sekretarza w formacie JSON
    @GetMapping("/employee/secretary")
    public Person getSecretary() {
        return employeeService.getKeyEmployees()[2];  // Zwróć sekretarza
    }
}
