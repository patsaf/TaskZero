package company.reports;

import company.employees.EmployeeRole;
import company.tasks.Task;
import company.tasks.TaskList;

public class Report {

    private final TaskList taskList;
    private final String firstName;
    private final String lastName;
    private final EmployeeRole role;
    private final int unitsOfWork;
    private final ReportList reportList;

    public Report(String firstName, String lastName, EmployeeRole role, int unitsOfWork) {

        taskList = new TaskList();
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.unitsOfWork = unitsOfWork;
        reportList = new ReportList();
    }

    public void addReport(Task task) {
        taskList.add(task);
    }

    public void ceoReport(Report report) { reportList.addReport(report);}

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public EmployeeRole getRole() { return role; }

    public int getUnitsOfWork() { return unitsOfWork; }

    public ReportList getReportList() { return reportList;  }

    @Override
    public String toString() {
        if(role == EmployeeRole.CEO) {
            return reportList.toString();
        } else {
            return firstName + " " + lastName + ", " + role.name() +
                    ", all work completed: " + unitsOfWork + " units\n" +
                    taskList.toString();
        }
    }
}
