package company;

import company.reports.Report;

public class MainClass {

    /**
     * arg[0] is the number of Development Managers
     * arg[1] is the maximum number of developers each manager can employ
     */
    public static void main(String[] args) {
        //generates a random company, hires employees and distributes work
        //CompanyGenerator companyGenerator = new CompanyGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        CompanyGenerator companyGenerator = new CompanyGenerator(2, 10);
        companyGenerator.assignRandomly();
        Report report = companyGenerator.getReports();
        companyGenerator.display();
        System.out.println(report);
    }

}
