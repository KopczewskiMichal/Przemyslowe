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

        model.addAttribute("employees", this.employeeManagementService.getEmployees());
        Set<String> countries = employeeManagementService.getDistrictCountries();
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
        model.addAttribute("person", person);
        Set<String> countries = employeeManagementService.getDistrictCountries();
        model.addAttribute("countries", countries);
        return "employeeForm";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("person") Person person, Model model) {
        Set<String> countries = employeeManagementService.getDistrictCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("errorMessage", "Could not save employee");
        String firstName = person.getFirstName();
        if (firstName.length() < 2 || firstName.length() > 50 || !firstName.matches("[a-zA-Z-]+")) {
            model.addAttribute("firstNameError", "First name must be between 2 and 50 characters and contain only letters and hyphens.");
        }

        // Walidacja nazwiska
        String lastName = person.getLastName();
        if (lastName.length() < 2 || lastName.length() > 50 || !lastName.matches("[a-zA-Z-]+")) {
            model.addAttribute("lastNameError", "Last name must be between 2 and 50 characters and contain only letters and hyphens.");
        }

        // Inne walidacje
        if (person.getSalary() <= 0 || person.getSalary() > 1000000) {
            model.addAttribute("salaryError", "Salary must be a positive number less than 1,000,000.");
        }

        // Jeśli są jakiekolwiek błędy, wróć do formularza
        if (model.containsAttribute("firstNameError") || model.containsAttribute("lastNameError") ||
                model.containsAttribute("salaryError") || model.containsAttribute("currencyError") ||
                model.containsAttribute("countryError")) {
            return "employeeForm";
        }

        // Zapisz lub zaktualizuj pracownika
        employeeManagementService.saveOrUpdateEmployee(person);

        return "redirect:/";  // Po zapisaniu, przekierowanie do listy pracowników
    }

    @PostMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeManagementService.deleteEmployee(id);
        return "redirect:/";
    }



}