package company.managers.managertypes;

import company.employees.Employee;
import company.employees.EmployeeRole;
import company.managers.TeamManager;

public class ManagerMale extends TeamManager {

    public ManagerMale(ManagerBuilder builder) {
        super(builder);
    }

    public boolean canHire(Employee employee) {
        if(super.canHire(employee)) {
            if(employee.getGender() == 'M') {
                return true;
            }
        }
        return false;
    }

    public static class ManagerMaleBuilder extends TeamManager.ManagerBuilder {

        public ManagerMaleBuilder(EmployeeRole role) {
            super(role);
        }

        protected ManagerMaleBuilder self() {
            return this;
        }

        public ManagerMale build() { return new ManagerMale(this); }
    }

}
