package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    // Metoda do zwrócenia najważniejszych pracowników w formacie JSON
    public Person[] getKeyEmployees() {
        return new Person[] { president, vicePresident, secretary };
    }
}
