package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class EmployeeManagementApplication implements CommandLineRunner {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @Autowired
    private CsvReaderService csvReaderService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeManagementService.setEmployees(csvReaderService.readEmployeesFromCsv("src/main/resources/MOCK_DATA.csv"));
        System.out.println("Pracownicy załadowani do systemu");
    }
}
