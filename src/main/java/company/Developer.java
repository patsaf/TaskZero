package company;

public class Developer extends AbstractEmployee implements Employee {

    public Developer(EmployeeRole role) {

        super(EmployeeType.DEVELOPER, role, 0);
    }

    @Override
    public Report reportWork() {
        return new Report(this, getUnitsOfWork());
    }

    @Override
    public void assign(Task task){
        setUnitsOfWork(task.getUnitsOfWork());
    }
}
