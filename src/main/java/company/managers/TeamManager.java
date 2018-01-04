package company.managers;

import company.employees.*;
import company.employees.details.*;
import company.predicates.PredicateFactory;
import company.predicates.PredicateInfo;
import company.reports.Report;
import company.reports.ReportList;
import company.tasks.Task;

import java.util.function.Predicate;

public class TeamManager extends AbstractEmployee implements Manager {

    private final EmployeeList list;
    private final int capacity;
    private final PredicateInfo conditionInfo;

    public TeamManager(ManagerBuilder builder) {
        super(builder);
        list = new EmployeeList();
        this.capacity = builder.capacity;
        this.conditionInfo = builder.conditionInfo;
    }

    public int getListSize() { return list.getSize(); }

    public Employee getListEmployee(int i) { return list.getEmployee(i);   }

    @Override
    public void assign(Task task, Employee employee) {
            list.sort();
            if(employee.getType() == EmployeeType.MANAGER) {
                TeamManager manager = (TeamManager) employee;
                manager.getTaskList().add(task);
                manager.setUnitsOfWork(task.getUnitsOfWork());
            } else {
                //Employee employee =  list.getEmployee(0);
                employee.assign(task, null);
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
        return (list.getSize() < capacity) && (makePredicate().test(employee));
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

    public Predicate<Employee> makePredicate(){
        switch(conditionInfo.getCondition()) {

            case EMPTY:
                return PredicateFactory.noCondition();
            case GENDER:
                Gender g = null;
                for(Gender x : Gender.values()) {
                    if(x.name().equalsIgnoreCase(conditionInfo.getConditionDetails())) {
                        g = x;
                    }
                }
                return PredicateFactory.chooseGender(g);
            case COUNTRY:
                return PredicateFactory.chooseCountry(new Country(conditionInfo.getConditionDetails()));
            case UNIVERSITY:
                return PredicateFactory.chooseUniversity(new University(conditionInfo.getConditionDetails()));
            case EMAIL:
                return PredicateFactory.chooseEmailDomain(conditionInfo.getConditionDetails());
            default:
                return PredicateFactory.noCondition();
        }
    }

    @Override
    public String toString() {
        String output = "";
        output += super.toString();
        output += conditionInfo;
        output += list;
        return output;
    }

    public int getCapacity() {
        return capacity;
    }

    public PredicateInfo getConditionInfo() {
        return conditionInfo;
    }

    public static class ManagerBuilder extends AbstractEmployee.Builder<ManagerBuilder> {

        private int capacity;
        private PredicateInfo conditionInfo;

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

        public ManagerBuilder hiringCondition(PredicateInfo conditionInfo) {
            this.conditionInfo = conditionInfo;
            return self();
        }


        public TeamManager build() {
            return new TeamManager(this);
        }
    }
}
