package company;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private final List<Employee> list;

    public EmployeeList() {
        list = new ArrayList<>();
    }

    public int getSize() { return list.size(); }

    public Employee getEmployee(int i) { return list.get(i); }

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    public void removeEmployee(Employee employee) {
        list.remove(employee);
    }

    @Override
    public String toString() {
        String output = "\n";
        for(Employee e: list) {
            output += "\t\t\"" + e.getRole() + "\"\n";
        }
        return output;
    }
}
