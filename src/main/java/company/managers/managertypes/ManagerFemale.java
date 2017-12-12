package company.managers.managertypes;

import company.employees.Employee;
import company.employees.EmployeeRole;
import company.managers.TeamManager;

public class ManagerFemale extends TeamManager{

    public ManagerFemale(ManagerBuilder builder) {
        super(builder);
    }

    public boolean canHire(Employee employee) {
        if(super.canHire(employee)) {
            if(employee.getGender() == 'K') {
                return true;
            }
        }
        return false;
    }

    public static class ManagerFemaleBuilder extends TeamManager.ManagerBuilder {

        public ManagerFemaleBuilder(EmployeeRole role) {
            super(role);
        }

        protected ManagerFemaleBuilder self() {
            return this;
        }

        public ManagerFemale build() { return new ManagerFemale(this); }
    }

}
