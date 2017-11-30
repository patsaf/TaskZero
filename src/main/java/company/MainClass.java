package company;

public class MainClass {

    /**
     * arg[0] is the number of Development Managers
     * arg[1] is the maximum number of developers each manager can employ
     */
    public static void main(String[] args) {
        CompanyGenerator company = new CompanyGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        company.display();
        company.assignRandomly();
        company.getReports();
    }

}
