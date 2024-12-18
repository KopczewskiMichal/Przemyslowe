package org.example.springintro;

public class Person {
    private int Id;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
    private String currency;
    private String country;

    public Person(String firstName, String lastName, String email, String salaryString, String currency, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = Double.parseDouble(salaryString);
        this.currency = currency;
        this.country = country;
    }

    public Person() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return String.format("%d: %s %s (%s) | Salary: %.2f %s | Country: %s",
                Id, firstName, lastName, email, salary, currency, country);
    }

    public String[] toStringArray() {
        return new String[] {
                String.valueOf(this.getId()),
                this.getFirstName(),
                this.getLastName(),
                this.getEmail(),
                String.valueOf(this.getSalary()),
                this.getCurrency(),
                this.getCountry()
        };
    }

    public void validateFields() {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be empty");
        }
    }

}
