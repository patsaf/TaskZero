package company;

public interface Employee {

    EmployeeType getType();
    EmployeeRole getRole();
    void setUnitsOfWork(int i);
    void assign(Task task);
    Report reportWork();
}
