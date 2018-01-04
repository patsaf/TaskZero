package company;

import company.employees.Developer;
import company.employees.EmployeeFactory;
import company.employees.details.EmployeeRole;
import company.employees.details.EmployeeType;
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
        ceo = (TeamManager) new EmployeeFactory(EmployeeType.MANAGER, EmployeeRole.CEO,
                ceoCapacity).getEmployee();
        hireManagers();
    }

    public void hireManagers() {
        int temp;
        for (int i=0; i<ceoCapacity; i++) {
            TeamManager manager;
            do {
                temp = r.nextInt(managerCapacity) + 1;
                manager = (TeamManager) new EmployeeFactory(EmployeeType.MANAGER,
                        EmployeeRole.DEVELOPMENT_MANAGER, temp).getEmployee();
            } while(!ceo.makePredicate().test(manager));
                temp = r.nextInt(managerCapacity) + 1;
                //TeamManager manager =
                hireDevelopers(temp, manager);
                ceo.hire(manager);
            }
        }

    public void hireDevelopers(int temp, TeamManager manager) {
        for(int j=0; j<temp; j++) {
            Developer developer;
            do {
                //chooses a random developer role - has to exclude the possibility
                //of choosing "CEO" and "Development Manager"
                developer = (Developer) new EmployeeFactory(EmployeeType.DEVELOPER,
                        EmployeeRole.values()[r.nextInt(EmployeeRole.values().length - 2) + 2],
                        0).getEmployee();

            } while(!manager.makePredicate().test(developer));
                manager.hire(developer);
        }
    }

    /*public void assignRandomly() {
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
    }*/

    public Report getReports() { return ceo.reportWork(); }

    public void display() {
        System.out.println(ceo);
    }
}