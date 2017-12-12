package company.managers;

import company.employees.Employee;

public interface Manager extends Employee {

    void hire(Employee employee);

    void fire(Employee employee);

    boolean canHire(Employee employee);

}

