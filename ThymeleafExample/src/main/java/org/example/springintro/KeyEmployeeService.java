package org.example.springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class KeyEmployeeService {

    private final Person president;
    private final Person vicePresident;
    private final Person secretary;

    @Autowired
    public KeyEmployeeService(@Qualifier("president") Person president,
                              @Qualifier("vicePresident") Person vicePresident,
                              @Qualifier("secretary") Person secretary) {
        this.president = president;
        this.vicePresident = vicePresident;
        this.secretary = secretary;
    }

    public Person[] getKeyEmployees() {
        return new Person[] { president, vicePresident, secretary };
    }
}
