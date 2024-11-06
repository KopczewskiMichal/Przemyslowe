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

    public String displayKeyEmployees() {
        StringBuilder result = new StringBuilder();
        result.append("President: ").append(president).append("\n");
        result.append("Vice President: ").append(vicePresident).append("\n");
        result.append("Secretary: ").append(secretary).append("\n");
        return result.toString();
    }
}

