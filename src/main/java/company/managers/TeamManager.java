package company.managers;

import company.employees.*;
import company.reports.Report;
import company.tasks.Task;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamManager extends AbstractEmployee implements Manager {

    private final EmployeeList list;
    private final int capacity;
    private final Report report;

    public TeamManager(ManagerBuilder builder) {
        super(builder);
        list = new EmployeeList();
        this.capacity = builder.capacity;
        report = new Report(getFirstName(), getLastName(), getRole(), getUnitsOfWork());
    }

    public int getListSize() { return list.getSize(); }

    public Employee getListEmployee(int i) { return list.getEmployee(i);   }

    @Override
    public void assign(Task task) {
            list.sort();
            Employee employee = list.getEmployee(0);
            if(employee.getType() == EmployeeType.MANAGER) {
                employee.reportWork().addReport(task);
                employee.setUnitsOfWork(task.getUnitsOfWork());
            } else {
                employee.assign(task);
            }
    }

    @Override
    public void hire(Employee employee) {
        if(canHire(employee)) {
            list.addEmployee(employee);
        } else {
            Logger log = Logger.getLogger(getClass().getName());
            log.log(Level.WARNING, "Your team is full!");
        }
    }

    @Override
    public void fire(Employee employee) {
        list.removeEmployee(employee);
    }

    @Override
    public boolean canHire(Employee employee) {
        if(list.getSize() < capacity) {
            return true;
        }
        return false;
    }

    @Override
    public Report reportWork() {
        TeamManager manager;
        Employee employee;
        if(getRole() == EmployeeRole.CEO) {
            for(int i=0; i<list.getSize(); i++) {
                manager = (TeamManager) list.getEmployee(i);
                report.ceoReport(manager.reportWork());
                for(int j=0; j<manager.getListSize();j++) {
                    employee = manager.getListEmployee(j);
                    report.ceoReport(employee.reportWork());
                }
            }
            report.getReportList().sort();
        }
        return report;
    }

    @Override
    public String toString() {
        String output = "";
        output += super.toString();
        output += list;
        return output;
    }

    public static class ManagerBuilder<T extends ManagerBuilder<T>> extends AbstractEmployee.Builder {
        private int capacity;

        public ManagerBuilder(EmployeeRole role) {
            super(EmployeeType.MANAGER, role);
        }

        protected ManagerBuilder self() {
            return this;
        }

        public ManagerBuilder capacity(int capacity) {
            this.capacity = capacity;
            return self();
        }

        public TeamManager build() {
            return new TeamManager(this);
        }
    }
}
