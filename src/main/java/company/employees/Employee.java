package company.employees;

import company.reports.Report;
import company.tasks.Task;

public interface Employee {

    EmployeeType getType();

    EmployeeRole getRole();
    
    int getUnitsOfWork();

    String getFirstName();

    String getLastName();

    String getEmail();

    char getGender();

    String getCountry();

    String getUniversity();

    void setUnitsOfWork(int i);

    void assign(Task task);

    Report reportWork();
}
