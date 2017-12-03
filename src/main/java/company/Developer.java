package company;

public class Developer extends AbstractEmployee implements Employee {

    public Developer(EmployeeRole role) {
        super(EmployeeType.DEVELOPER, role);
    }

    @Override
    public void assign(Task task){
        getReport().addReport(task);
        setUnitsOfWork(task.getUnitsOfWork());
    }
}
