package company.managers.managertypes;

import company.employees.Employee;
import company.employees.EmployeeRole;
import company.employees.EmployeeType;
import company.managers.TeamManager;

public class ManagerAGH extends TeamManager{

    public ManagerAGH(ManagerAGHBuilder builder) {
        super(builder);
    }

    public boolean canHire(Employee employee) {
            if(super.canHire(employee)) {
                if(employee.getUniversity() == "AGH") {
                    return true;
                }
            }
        return false;
    }

    public static class ManagerAGHBuilder extends TeamManager.ManagerBuilder {

        public ManagerAGHBuilder(EmployeeRole role) {
            super(role);
        }

        protected ManagerAGHBuilder self() {
            return this;
        }

        public ManagerAGH build() { return new ManagerAGH(this); }
    }

}
