package company.employees;

import company.employees.details.EmployeeRole;
import company.employees.details.EmployeeType;
import company.reports.Report;
import company.tasks.Task;

public class Developer extends AbstractEmployee implements Employee {


    public Developer(DeveloperBuilder builder) {
        super(builder);
    }

    @Override
    public void assign(Task task, Employee employee){
        getTaskList().add(task);
        setUnitsOfWork(task.getUnitsOfWork());
    }

    @Override
    public Report reportWork() {
        return new Report(this);
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
