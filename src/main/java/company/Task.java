package company;

import java.util.Random;

public class Task {

    private final String[] managerTasks = {"Distribute work", "Supervise work", "Write reports"};
    private final String[] developerTasks = {"Implement a new bug", "Write code review", "Process data"};
    private final String[] testerTasks = {"Test a new bug", "Implement a series of tests"};
    private final Random r = new Random();
    private final Employee employee;
    private final int employeeIndex;
    private final int unitsOfWork;
    private String task;

    public Task(Employee employee, int unitsOfWork, int employeeIndex) {
        this.employee = employee;
        this.employeeIndex = employeeIndex;
        this.unitsOfWork = unitsOfWork;

        switch(employee.getType()) {

            case MANAGER:
                if(employee.getRole() == EmployeeRole.DEVELOPMENTMANAGER) {
                    task = managerTasks[r.nextInt(managerTasks.length)];
                }
                break;
            case DEVELOPER:
                switch(employee.getRole()) {
                    case TESTER:
                        task = testerTasks[r.nextInt(testerTasks.length)];
                        break;
                    case DEVELOPER:
                        task = developerTasks[r.nextInt(developerTasks.length)];
                        break;
                    case CONTRIBUTOR:
                        task = developerTasks[r.nextInt(developerTasks.length)];
                        break;
                    case TEAMLEADER:
                        task = managerTasks[r.nextInt(managerTasks.length)];
                        break;
                }
                break;
            default:
                task = "";
                break;
        }
    }

    public String getTask() {
        return task;
    }

    public int getUnitsOfWork() { return unitsOfWork; }

    public int getEmployeeIndex() { return employeeIndex; }

}
