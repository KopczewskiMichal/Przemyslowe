package org.example.springintro;

import org.example.springintro.Person;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            employees = br.lines().skip(1)
                    .map(line -> line.split(","))
                    .map(data -> new Person(data[0], data[1], data[2], data[5]))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
