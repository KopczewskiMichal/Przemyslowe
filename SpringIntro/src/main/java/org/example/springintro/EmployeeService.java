package org.example.springintro;

import org.example.springintro.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final Person president;
    private final Person vicePresident;
    private final Person secretary;

    @Autowired
    public EmployeeService(@Qualifier("president") Person president,
                           @Qualifier("vicePresident") Person vicePresident,
                           @Qualifier("secretary") Person secretary) {
        this.president = president;
        this.vicePresident = vicePresident;
        this.secretary = secretary;
    }

    public void displayKeyEmployees() {
        System.out.println("President: " + president);
        System.out.println("Vice President: " + vicePresident);
        System.out.println("Secretary: " + secretary);
    }
}

