package company;

import company.employees.Employee;
import company.employees.details.EmployeeRole;
import company.employees.details.EmployeeType;
import company.managers.TeamManager;

public class Display {

    public Display() {}

    public String displayManagerBrief(Employee manager) {
        return manager.getRole().name() + " " + manager.getFirstName() + " " + manager.getLastName();
    }

    public String displayManager(Employee employee) {
        if(employee.getType().equals(EmployeeType.MANAGER)) {
            TeamManager manager = (TeamManager) employee;
            if(employee.getRole().equals(EmployeeRole.CEO)) {
                return "Email: " + manager.getEmail() + "\n" + "Gender: " + manager.getGender().name().toLowerCase() + "\n" + "University: " +
                        manager.getUniversity() + "\n" + "Country: " + manager.getCountry() + "\n\n" +
                        "Can hire up to " + manager.getCapacity() + " managers" + "\n" + manager.getConditionInfo();
            } else {
                return "Email: " + manager.getEmail() + "\n" + "Gender: " + manager.getGender().name().toLowerCase() + "\n" + "University: " +
                        manager.getUniversity() + "\n" + "Country: " + manager.getCountry() + "\n\n" +
                        "Can hire up to " + manager.getCapacity() + " developers" + "\n" + manager.getConditionInfo();
            }
        } else {
            return "Email: " + employee.getEmail() + "\n" + "Gender: " + employee.getGender().name().toLowerCase() + "\n" + "University: " +
                    employee.getUniversity() + "\n" + "Country: " + employee.getCountry() + "\n";
        }
    }
}
