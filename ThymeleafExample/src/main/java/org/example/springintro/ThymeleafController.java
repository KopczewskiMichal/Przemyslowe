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

    @GetMapping("/employee-form")
    public String showEmployeeForm(@RequestParam(required = false, defaultValue = "0") int id, Model model) {
        Person person = id == 0 ? new Person() : employeeManagementService.getEmployeeById(id);
        model.addAttribute("person", person);
        return "employeeForm";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Person person, Model model) {
        try {
            person.validateFields();
            employeeManagementService.saveOrUpdateEmployee(person);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("person", person);
            return "employeeForm";
        }
    }

}