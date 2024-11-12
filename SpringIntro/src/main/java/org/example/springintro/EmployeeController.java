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
