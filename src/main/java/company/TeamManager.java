package company;

public class TeamManager extends AbstractEmployee implements Manager {

    private final EmployeeList list;

    public TeamManager(EmployeeRole role, int capacity) {
        super(EmployeeType.MANAGER, role, capacity);
        list = new EmployeeList();
    }

    public EmployeeList getList() { return list; }

    @Override
    public void assign(Task task) {
            System.out.println("********* ASSIGNMENT **********");
            System.out.println("Assigned by: " + getRole());
            System.out.println("Assigned to: " + list.getEmployee(task.getEmployeeIndex()).getRole());
            System.out.println("TASK: " + task.getTask() + " - " + task.getUnitsOfWork() + " units of work\n");
            Employee employee = list.getEmployee(task.getEmployeeIndex());
            if(employee.getRole() == EmployeeRole.DEVELOPMENTMANAGER) {
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
        if(list.getSize() < getCapacity()) {
            return true;
        }
        return false;
    }

    @Override
    public Report reportWork() {
        return new Report(this, getUnitsOfWork());
    }

    @Override
    public String toString() {
        switch(getRole()) {
            case CEO:
                return "\"" + getRole() + "\" {";
            case DEVELOPMENTMANAGER:
                return "\t\"" + getRole() + "\" {" + list + "\t}";
            default:
                throw new IllegalArgumentException();
        }
    }
}
