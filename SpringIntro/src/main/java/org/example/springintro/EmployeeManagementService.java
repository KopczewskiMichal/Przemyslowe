package org.example.springintro;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeManagementService {

    public List<Person> filterByCompany(List<Person> employees, String company) {
        return employees.stream()
                .filter(employee -> employee.getCompany().equalsIgnoreCase(company))
                .collect(Collectors.toList());
    }

    public List<Person> sortByLastName(List<Person> employees) {
        return employees.stream()
                .sorted((p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName()))
                .collect(Collectors.toList());
    }

}
