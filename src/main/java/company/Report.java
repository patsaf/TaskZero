package company;

public class Report {

    public Report(Employee employee, int units) {
        System.out.println("******** REPORT *********");
        System.out.println("Work completed by " + employee.getRole() + " " + units + " units of work\n");
    }
}
