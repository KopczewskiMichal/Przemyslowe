package org.example.springintro;

public class Person {
    private int Id;  // ID będzie ustawiane przez setter
    private String firstName;
    private String lastName;
    private String email;
    private String company;

    // Konstruktor bez ID
    public Person(String firstName, String lastName, String email, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
    }

    // Setter dla ID
    public void setId(int Id) {
        this.Id = Id;
    }

    // Gettery i settery
    public int getId() { return Id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    @Override
    public String toString() {
        return String.format("%d: %s %s (%s) - %s", Id, firstName, lastName, email, company);
    }
}
