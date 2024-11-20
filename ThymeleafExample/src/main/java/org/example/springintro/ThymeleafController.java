package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
public class ThymeleafController {

    private final EmployeeManagementService employeeManagementService;
    private final EmployeeService employeeService;

    @Autowired
    public ThymeleafController(EmployeeManagementService employeeManagementService, EmployeeService employeeService) {
        this.employeeManagementService = employeeManagementService;
        this.employeeService = employeeService;
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "Witaj na stronie Thymeleaf!");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("totalEmployees", this.employeeManagementService.getEmployeesCount() + 3); // 3 pracowników kluczowych
        model.addAttribute("keyEmployees", this.employeeService.getKeyEmployees());
        model.addAttribute("employees", this.employeeManagementService.getEmployees());
        return "mainPage";
    }

    @GetMapping("/employee-by-id/{id}")
    public String getEmployeeById(Model model, @PathVariable int id) {
        Person employee = employeeManagementService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }


}