package company;

public interface Employee {

    EmployeeType getType();
    EmployeeRole getRole();
    void setUnitsOfWork(int i);
    int getUnitsOfWork();
    Report getReport();
    void assign(Task task);
    Report reportWork();
}
