package company.managers.managertypes;

import company.employees.Employee;
import company.employees.EmployeeRole;
import company.managers.TeamManager;

public class ManagerGmail extends TeamManager {

    public ManagerGmail(ManagerBuilder builder) {
        super(builder);
    }

    public boolean canHire(Employee employee) {
        if(super.canHire(employee)) {
            if(employee.getEmail().contains("gmail.com")) {
                return true;
            }
        }
        return false;
    }

    public static class ManagerGmailBuilder extends TeamManager.ManagerBuilder {

        public ManagerGmailBuilder(EmployeeRole role) {
            super(role);
        }

        protected ManagerGmailBuilder self() {
            return this;
        }

        public ManagerGmail build() { return new ManagerGmail(this); }
    }

}
