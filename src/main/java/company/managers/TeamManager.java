package company.managers;

import company.employees.*;
import company.employees.details.EmployeeRole;
import company.employees.details.EmployeeType;
import company.reports.Report;
import company.reports.ReportList;
import company.tasks.Task;

import java.util.function.Predicate;

public class TeamManager extends AbstractEmployee implements Manager {

    private final EmployeeList list;
    private final int capacity;
    private final Predicate<Employee> hiringCondition;

    public TeamManager(ManagerBuilder builder) {
        super(builder);
        list = new EmployeeList();
        this.capacity = builder.capacity;
        this.hiringCondition = builder.hiringCondition;
    }

    public int getListSize() { return list.getSize(); }

    public Predicate<Employee> getHiringCondition() {
        return hiringCondition;
    }

    public Employee getListEmployee(int i) { return list.getEmployee(i);   }

    @Override
    public void assign(Task task) {
            list.sort();
            if(list.getEmployee(0).getType() == EmployeeType.MANAGER) {
                TeamManager manager = (TeamManager) list.getEmployee(0);
                manager.getTaskList().add(task);
                manager.setUnitsOfWork(task.getUnitsOfWork());
            } else {
                Employee employee =  list.getEmployee(0);
                employee.assign(task);
            }
    }

    @Override
    public void hire(Employee employee) {
        if(canHire(employee)) {
            list.addEmployee(employee);
        }
    }

    @Override
    public void fire(Employee employee) {
        list.removeEmployee(employee);
    }

    @Override
    public boolean canHire(Employee employee) {
        return (list.getSize() < capacity) && (hiringCondition.test(employee));
    }

    @Override
    public Report reportWork() {
        TeamManager manager;
        Employee employee;
        ReportList reportList = new ReportList();
        if(getRole() == EmployeeRole.CEO) {
            for(int i=0; i<list.getSize(); i++) {
                manager = (TeamManager) list.getEmployee(i);
                reportList.add(new Report(manager));
                for(int j=0; j<manager.getListSize();j++) {
                    employee = manager.getListEmployee(j);
                    reportList.add(new Report(employee));
                }
            }
            return new Report(this, reportList);
        }
        return new Report(this);
    }

    @Override
    public String toString() {
        String output = "";
        output += super.toString();
        output += list;
        return output;
    }

    public static class ManagerBuilder extends AbstractEmployee.Builder<ManagerBuilder> {

        private int capacity;
        private Predicate<Employee> hiringCondition;

        public ManagerBuilder(EmployeeRole role) {
            super(EmployeeType.MANAGER, role);
        }

        public ManagerBuilder self() {
            return this;
        }

        public ManagerBuilder capacity(int capacity) {
            this.capacity = capacity;
            return self();
        }

        public ManagerBuilder hiringCondition(Predicate<Employee> predicate) {
            this.hiringCondition = predicate;
            return self();
        }

        public TeamManager build() {
            return new TeamManager(this);
        }
    }
}
