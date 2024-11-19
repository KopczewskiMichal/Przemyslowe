package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        List<String[]> employeeAttributes = this.employeeManagementService.getEmployees().stream()
                .map(employee -> new String[] {
                        String.valueOf(employee.getId()),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getCompany(),
                        employee.getEmail(),
                })
                .toList();

        model.addAttribute("employees", employeeAttributes);
        return "mainPage";
    }


}