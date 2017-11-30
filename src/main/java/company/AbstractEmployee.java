package company;

public abstract class AbstractEmployee implements Employee {

    private final EmployeeType type;
    private final EmployeeRole role;
    private final int capacity;
    private int unitsOfWork;

    public AbstractEmployee(EmployeeType type, EmployeeRole role, int capacity) {
        this.type = type;
        this.role = role;
        this.capacity = capacity;
        unitsOfWork = 0;
    }

    public int getCapacity() { return capacity; }

    @Override
    public EmployeeType getType() {
        return type;
    }

    @Override
    public EmployeeRole getRole() {
        return role;
    }

    public int getUnitsOfWork() {
        return unitsOfWork;
    }

    @Override
    public void setUnitsOfWork(int i) {
        unitsOfWork += i;
    }
}
