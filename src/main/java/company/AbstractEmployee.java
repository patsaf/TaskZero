package company;

public abstract class AbstractEmployee implements Employee {

    private final EmployeeType type;
    private final EmployeeRole role;
    private int unitsOfWork;
    Report report;

    public AbstractEmployee(EmployeeType type, EmployeeRole role) {
        this.type = type;
        this.role = role;
        unitsOfWork = 0;
        report = new Report();
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
    public Report getReport() { return report; }

    @Override
    public Report reportWork() {
        System.out.println("Work reported by: " + getRole().name());
        System.out.println("All work completed: "+ getUnitsOfWork() + " units");
        return report;
    }
}
