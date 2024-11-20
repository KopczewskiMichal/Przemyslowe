package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class ThymeleafController {

    private final EmployeeManagementService employeeManagementService;
    private final KeyEmployeeService keyEmployeeService;

    @Autowired
    public ThymeleafController(EmployeeManagementService employeeManagementService, KeyEmployeeService employeeService) {
        this.employeeManagementService = employeeManagementService;
        this.keyEmployeeService = employeeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("totalEmployees", this.employeeManagementService.getEmployeesCount() + 3); // 3 pracowników kluczowych
        model.addAttribute("keyEmployees", this.keyEmployeeService.getKeyEmployees());
        model.addAttribute("salaryByCurrency", employeeManagementService.salaryByCurrency());

        Set<String> countries = employeeManagementService.getDistrictCountries();
        model.addAttribute("employees", this.employeeManagementService.getEmployees());
        model.addAttribute("countries", countries);
        return "mainPage";
    }

    @GetMapping("/employee-by-id/{id}")
    public String getEmployeeById(Model model, @PathVariable int id) {
        Person employee = employeeManagementService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("totalEmployees", this.employeeManagementService.getEmployeesCount() + 3); // 3 pracowników kluczowych

        return "employeeDetails";
    }

    @GetMapping("/employee-form/{id}")
    public String showEmployeeForm(Model model, @PathVariable int id) {
        Person person;
        if (id == 0) {
            person = new Person();  // Nowy pracownik
        } else {
            person = employeeManagementService.getEmployeeById(id);  // Edytowanie istniejącego
        }
        person.validateFields();  // Weryfikacja pól (zakładając, że masz taką metodę)
        model.addAttribute("person", person);
        return "employeeForm";
    }





    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Person person) {
        person.validateFields();
        if (person.getId() == 0) {
            employeeManagementService.saveOrUpdateEmployee(person);
        } else {
            employeeManagementService.saveOrUpdateEmployee(person);
        }
        return "redirect:/";
    }

    @PostMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeManagementService.deleteEmployee(id);
        return "redirect:/";
    }



}