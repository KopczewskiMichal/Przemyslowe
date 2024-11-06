package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeManagementService employeeManagementService;

    @Autowired
    public EmployeeController(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    // 1. Wyświetl wszystkich użytkowników w formacie JSON
    @GetMapping
    public List<Person> getAllEmployees() {
        return employeeManagementService.getEmployees();
    }

    // 2. Wyświetl konkretnego użytkownika w formacie JSON po ID
    @GetMapping("/{id}")
    public Person getEmployeeById(@PathVariable int id) {
        return employeeManagementService.getEmployees().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);  // Jeśli nie znajdziemy użytkownika, zwróci null
    }

    // 3. Dodaj użytkownika do bazy użytkowników
    @PostMapping
    public String addEmployee(@RequestBody Person newEmployee) {
        employeeManagementService.addEmployee(newEmployee);
        return "Użytkownik został dodany!";
    }

    // 4. Edytuj użytkownika w bazie użytkowników
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Person updatedEmployee) {
        boolean updated = employeeManagementService.updateEmployee(id, updatedEmployee);
        if (updated) {
            return "Użytkownik zaktualizowany!";
        } else {
            return "Użytkownik o podanym ID nie istnieje!";
        }
    }

    // 5. Usuń użytkownika z bazy użytkowników
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        boolean deleted = employeeManagementService.deleteEmployee(id);
        if (deleted) {
            return "Użytkownik usunięty!";
        } else {
            return "Użytkownik o podanym ID nie istnieje!";
        }
    }
}
