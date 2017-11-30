package company;

public enum EmployeeRole {
    CEO, DEVELOPMENTMANAGER, TESTER, DEVELOPER, CONTRIBUTOR, TEAMLEADER;

    @Override
    public String toString() {
        switch(this) {
            case CEO:
                return "CEO";
            case DEVELOPMENTMANAGER:
                return "Development Manager";
            case TESTER:
                return "Tester";
            case DEVELOPER:
                return "Developer";
            case CONTRIBUTOR:
                return "Contributor";
            case TEAMLEADER:
                return "Team Leader";
            default:
                throw new IllegalArgumentException();
        }
    }
}
