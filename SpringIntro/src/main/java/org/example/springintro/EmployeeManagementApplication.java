package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class EmployeeManagementApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CsvReaderService csvReaderService;

    @Autowired
    private EmployeeManagementService employeeManagementService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Tworzymy osobny wątek, aby uruchomić logikę konsolową bez blokowania serwera HTTP
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            employeeService.displayKeyEmployees();

            List<Person> employees = csvReaderService.readEmployeesFromCsv("src/main/resources/MOCK_DATA.csv");

            while (true) {
                System.out.println("1. Wyświetl najważniejszych pracowników");
                System.out.println("2. Wyświetl wszystkich pracowników");
                System.out.println("3. Wyświetl pracowników z Chin");
                System.out.println("4. Sortuj pracowników wg. nazwiska");
                System.out.println("0. Opuść program");
                System.out.print("Wybierz opcje: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        employeeService.displayKeyEmployees();
                        break;

                    case 2:
                        System.out.println("\nWszyscy pracownicy:");
                        employees.forEach(System.out::println);
                        break;

                    case 3:
                        List<Person> filteredEmployees = employeeManagementService.filterByCompany(employees, "China");
                        System.out.println("\nPracownicy pracujący w Twitterbridge:");
                        filteredEmployees.forEach(System.out::println);
                        break;

                    case 4:
                        List<Person> sortedEmployees = employeeManagementService.sortByLastName(employees);
                        System.out.println("\nPracownicy posortowani po nazwisku:");
                        sortedEmployees.forEach(System.out::println);
                        break;

                    case 0:
                        System.out.println("Opuszczono aplikację");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Wprowadzono niepoprawną opcję. Spróbuj ponownie.");
                }
            }
        }).start(); // Uruchom wątek z logiką konsolową
    }

    // Wewnętrzny kontroler do obsługi żądań HTTP
    @RestController
    public class HelloController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello, world!";
        }
    }
}
