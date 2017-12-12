package company.employees;

import company.reports.Report;
import company.tasks.Task;

public class Developer extends AbstractEmployee implements Employee {

    private final Report report;

    public Developer(DeveloperBuilder builder) {
        super(builder);
        report = new Report(getFirstName(), getLastName(), getRole(), getUnitsOfWork());
    }

    @Override
    public void assign(Task task){
        report.addReport(task);
        setUnitsOfWork(task.getUnitsOfWork());
    }

    @Override
    public Report reportWork() {
        return report;
    }

    public static class DeveloperBuilder extends AbstractEmployee.Builder {

        public DeveloperBuilder(EmployeeRole role) {
            super(EmployeeType.DEVELOPER, role);
        }

        protected DeveloperBuilder self() {
            return this;
        }

        public Developer build() {
            return new Developer(this);
        }
    }
}
