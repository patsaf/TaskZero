package company;

import company.employees.Developer;
import company.employees.EmployeeFactory;
import company.employees.EmployeeRole;
import company.employees.EmployeeType;
import company.managers.TeamManager;
import company.reports.Report;
import company.tasks.ManagerTaskFactory;
import company.tasks.Task;
import company.tasks.TaskFactory;

import java.util.Random;

public class CompanyGenerator {

    private final TeamManager ceo;
    private final Random r = new Random();
    private final int ceoCapacity;
    private final int managerCapacity;

    public CompanyGenerator(int ceoCapacity, int managerCapacity) {
        this.ceoCapacity = ceoCapacity;
        this.managerCapacity = managerCapacity;
        ceo = (TeamManager) new EmployeeFactory(EmployeeType.MANAGER, EmployeeRole.CEO, ceoCapacity).getEmployee();
        hireManagers();
    }

    public void hireManagers() {
        int temp;
        for (int i=0; i<ceoCapacity; i++) {
            temp = r.nextInt(managerCapacity)+1;
            TeamManager manager = (TeamManager) new EmployeeFactory(EmployeeType.MANAGER, EmployeeRole.DEVELOPMENT_MANAGER,
                    temp).getEmployee();
            hireDevelopers(temp, manager);
            ceo.hire(manager);
        }
    }

    public void hireDevelopers(int temp, TeamManager manager) {
        for(int j=0; j<temp; j++) {
            //chooses a random developer role - has to exclude the possibility
            //of choosing "CEO" and "Development Manager"
            manager.hire((Developer) new EmployeeFactory(EmployeeType.DEVELOPER,
                    EmployeeRole.values()[r.nextInt(EmployeeRole.values().length-2)+2],0).getEmployee());
        }
    }

    public void assignRandomly() {
        Task task;
        for(int i=0; i<r.nextInt(10); i++) {
            task = new Task(new ManagerTaskFactory().getTaskName(), r.nextInt(20));
            ceo.assign(task);
        }

        for(int j=0; j<ceo.getListSize(); j++) {
            TeamManager manager = (TeamManager) ceo.getListEmployee(j);
            for (int i=0; i<r.nextInt(15); i++) {
                task = new Task(new TaskFactory().getTaskName(), r.nextInt(20));
                manager.assign(task);
            }
        }
    }

    public Report getReports() { return ceo.reportWork(); }

    public void display() {
        System.out.println(ceo);
    }
}