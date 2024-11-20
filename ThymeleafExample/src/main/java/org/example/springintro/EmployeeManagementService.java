package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeManagementService {

    private List<Person> employees;

    @Autowired
    private CsvReaderService csvReaderService;

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }

    public void addEmployee(Person newEmployee) {
        employees.add(newEmployee);
    }

    public boolean updateEmployee(int id, Person updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                employees.set(i, updatedEmployee);
                return true;
            }
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        return employees.removeIf(employee -> employee.getId() == id);
    }

    public List<Person> sortByLastName() {
        return employees.stream()
                .sorted((p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName()))
                .collect(Collectors.toList());
    }

    public Person getEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + id + " not found."));
    }

    public int getEmployeesCount() {
        return employees.size();
    }

    public Set<String> getDistrictCountries() {
        return employees.stream()
                .map(Person::getCountry)
                .collect(Collectors.toSet());
    }

    public Map<String, Double> salaryByCurrency() {
        Map<String, Double> salaryByCurrency = new HashMap<>();

        for (Person employee : employees) {
            String currency = employee.getCurrency();
            double salary = employee.getSalary();

            salaryByCurrency.put(currency, salaryByCurrency.getOrDefault(currency, 0.0) + salary);
        }
        return salaryByCurrency;
        }

    private int getNextId() {
        return this.employees.get(this.employees.size() - 1).getId() + 1;
    }

    public void saveOrUpdateEmployee(Person person) {
        
        if (person.getId() == 0) {
            person.setId(this.getNextId());
            employees.add(person);
        } else {
            employees.replaceAll(e -> e.getId() == person.getId() ? person : e);
        }
    }

    public boolean isEmailAlreadyTaken(String email, int id) {
        return employees.stream().anyMatch(person -> person.getEmail() == email || person.getId() != id);
    }


}

