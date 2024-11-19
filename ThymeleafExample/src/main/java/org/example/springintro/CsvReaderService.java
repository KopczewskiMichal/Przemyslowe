package org.example.springintro;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvReaderService {
    public List<Person> readEmployeesFromCsv(String filePath) {
        List<Person> employees = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            int idCounter = 1;
            employees = br.lines()
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(data -> new Person(data[0], data[1], data[2], data[3], data[1], data[5]))
                    .collect(Collectors.toList());
            for (Person person : employees) {
                person.setId(idCounter++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
