package company;

import java.util.Random;

public class CompanyGenerator {

    private final TeamManager ceo;
    private final Random r = new Random();
    private final int ceoCapacity;
    private final int managerCapacity;

    public CompanyGenerator(int ceoCapacity, int managerCapacity) {
        this.ceoCapacity = ceoCapacity;
        this.managerCapacity = managerCapacity;
        ceo = new TeamManager(EmployeeRole.CEO, ceoCapacity);
        hireManagers();
    }

    public void hireManagers() {
        int temp;
        for (int i=0; i<ceoCapacity; i++) {
            temp = r.nextInt(managerCapacity)+1;
            TeamManager manager = new TeamManager(EmployeeRole.DEVELOPMENTMANAGER, temp);
            hireDevelopers(temp, manager);
            ceo.hire(manager);
        }
    }

    public void hireDevelopers(int temp, TeamManager manager) {
        for(int j=0; j<temp; j++) {
            //chooses a random developer role - has to exclude the possibility
            //of choosing "CEO" and "Development Manager"
            manager.hire(new Developer(EmployeeRole.values()[r.nextInt(EmployeeRole.values().length-2)+2]));
        }
    }

    public void assignRandomly() {
        Task task;
        int randomIndex;
        for(int i=0; i<r.nextInt(10); i++) {
            randomIndex = r.nextInt(ceo.getList().getSize());
            task = new Task(ceo.getList().getEmployee(randomIndex), r.nextInt(20), randomIndex);
            ceo.assign(task);
        }

        for(int j=0; j<ceo.getList().getSize(); j++) {
            TeamManager manager = (TeamManager) ceo.getList().getEmployee(j);
            for (int i = 0; i < r.nextInt(15); i++) {
                randomIndex = r.nextInt(manager.getList().getSize());
                task = new Task(manager.getList().getEmployee(randomIndex), r.nextInt(20), randomIndex);
                manager.assign(task);
            }
        }
    }

    public void getReports() {
        Report report;
        for(int i=0; i<ceo.getList().getSize(); i++) {
            TeamManager manager = (TeamManager) ceo.getList().getEmployee(i);
            report = manager.reportWork();
            for(int j=0; j<manager.getList().getSize(); j++) {
                report = manager.getList().getEmployee(j).reportWork();
            }
        }
    }

    public void display() {
        System.out.println(ceo);
        for(int i = 0; i < ceo.getList().getSize(); i++){
            System.out.println(ceo.getList().getEmployee(i));
        }
        System.out.println("}\n");
    }
}
