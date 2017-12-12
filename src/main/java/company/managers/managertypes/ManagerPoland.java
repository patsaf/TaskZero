package company.managers.managertypes;

import company.employees.Employee;
import company.employees.EmployeeRole;
import company.managers.TeamManager;

public class ManagerPoland extends TeamManager {

    public ManagerPoland(ManagerBuilder builder) {
        super(builder);
    }

    public boolean canHire(Employee employee) {
        if(super.canHire(employee)) {
            if(employee.getCountry() == "Polska") {
                return true;
            }
        }
        return false;
    }

    public static class ManagerPolandBuilder extends TeamManager.ManagerBuilder {

        public ManagerPolandBuilder(EmployeeRole role) {
            super(role);
        }

        protected ManagerPolandBuilder self() {
            return this;
        }

        public ManagerPoland build() { return new ManagerPoland(this); }
    }

}
