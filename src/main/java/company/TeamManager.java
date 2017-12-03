package company;

import java.util.Random;

public class TeamManager extends AbstractEmployee implements Manager {

    private final EmployeeList list;
    private final int capacity;
    private final Random r = new Random();

    public TeamManager(EmployeeRole role, int capacity) {
        super(EmployeeType.MANAGER, role);
        list = new EmployeeList();
        this.capacity = capacity;
    }

    public EmployeeList getList() { return list; }

    @Override
    public void assign(Task task) {
            list.sort();
            Employee employee = list.getEmployee(0);
            if(employee.getRole() == EmployeeRole.DEVELOPMENT_MANAGER) {
                employee.getReport().addReport(task);
                employee.setUnitsOfWork(task.getUnitsOfWork());
            } else {
                employee.assign(task);
            }
    }

    @Override
    public void hire(Employee employee) {
        if(canHire()) {
            list.addEmployee(employee);
        } else {
            System.out.println("Your team is full!");
        }
    }

    @Override
    public void fire(Employee employee) {
        list.removeEmployee(employee);
    }

    @Override
    public boolean canHire() {
        if(list.getSize() < capacity) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(getRole() == EmployeeRole.CEO) {
            return "\"" + getRole().name() + "\" {";
        } else {
            return "\t\"" + getRole().name() + "\" {" + list + "\t}";
        }
    }
}
