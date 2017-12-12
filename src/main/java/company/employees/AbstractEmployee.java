package company.employees;

import java.util.regex.Pattern;

public abstract class AbstractEmployee implements Employee {

    private final EmployeeType type;
    private final EmployeeRole role;
    private final String firstName;
    private final String lastName;
    private final String university;
    private final char gender;
    private final String country;
    private final String email;
    private int unitsOfWork;

    public AbstractEmployee(Builder builder) {
        this.type = builder.type;
        this.role = builder.role;
        this.university = builder.university;
        this.country = builder.country;
        unitsOfWork = 0;

        if (Pattern.matches("[A-Z][a-z]+", builder.firstName)) {
            this.firstName = builder.firstName;
        } else {
            throw new IllegalArgumentException();
        }

        if (Pattern.matches("[A-Z][a-z]+", builder.lastName)) {
            this.lastName = builder.lastName;
        } else {
            throw new IllegalArgumentException();
        }

        if (Pattern.matches("[a-z0-9_-]+@[a-z]+\\.[a-z]{2,3}", builder.email)) {
            this.email = builder.email;
        } else {
            throw new IllegalArgumentException();
        }

        if ((builder.gender == 'K') || (builder.gender == 'M')) {
            this.gender = builder.gender;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public EmployeeType getType() {
        return type;
    }

    @Override
    public EmployeeRole getRole() {
        return role;
    }

    @Override
    public int getUnitsOfWork() {
        return unitsOfWork;
    }

    @Override
    public void setUnitsOfWork(int i) {
        unitsOfWork += i;
    }

    @Override
    public String getFirstName() { return firstName; }

    @Override
    public String getLastName() { return lastName; }

    @Override
    public String getUniversity() { return university; }

    @Override
    public char getGender() { return gender; }

    @Override
    public String getCountry() { return country; }

    @Override
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return role.name() + " { "  + firstName +
                " " + lastName + ", " + gender + ", " + email +
                ", " + country + ", " + university + " }\n";
    }

    public static abstract class Builder<T extends Builder<T>> {

        private  EmployeeType type;
        private  EmployeeRole role;
        private  String firstName;
        private  String lastName;
        private  String university;
        private  char gender;
        private  String country;
        private  String email;

        protected abstract T self();

        public Builder(EmployeeType type, EmployeeRole role) {
            this.role = role;
            this.type = type;
        }

        public T name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            return self();
        }

        public T university(String university) {
            this.university = university;
            return self();
        }

        public T gender(char gender) {
            this.gender = gender;
            return self();
        }

        public T country(String country) {
            this.country = country;
            return self();
        }

        public T email(String email) {
            this.email = email;
            return self();
        }

        public abstract Employee build();

        public T capacity(int capacity) { return self(); }
    }
}

