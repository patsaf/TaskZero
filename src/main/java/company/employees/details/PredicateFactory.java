package company.employees.details;

import company.employees.Employee;
import java.util.function.Predicate;

public final class PredicateFactory {

    public static final Predicate<Employee> noCondition() {
        return employee -> true;
    }

    public static final Predicate<Employee> chooseUniversity(University university) {
        return employee -> employee.getUniversity().equals(university);
    }

    public static final Predicate<Employee> chooseGender(Gender gender) {
        return employee -> employee.getGender().equals(gender);
    }

    public static final Predicate<Employee> chooseCountry(Country country) {
        return employee -> employee.getCountry().equals(country);
    }

    public static final Predicate<Employee> chooseEmailDomain(String domain) {
        return employee -> employee.getEmail()
                .toString()
                .endsWith(domain);
    }

    public static final Predicate<Employee> chooseRole(EmployeeRole role) {
        return employee -> employee.getRole().equals(role);
    }
}
