package org.example.springintro;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String company;

    public Person(String firstName, String lastName, String email, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
    }

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
        return String.format("%s %s (%s) - %s", firstName, lastName, email, company);
    }
}
