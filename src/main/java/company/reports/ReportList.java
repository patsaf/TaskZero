package company.reports;

import java.util.ArrayList;
import java.util.List;

public class ReportList {

    private final List<Report> listOfReports;

    public ReportList() {
        listOfReports = new ArrayList<>();
    }

    public void addReport(Report report) {
        listOfReports.add(report);
    }

    public void sort() {
        listOfReports.sort((o1,o2) -> {
            int cmp = o1.getLastName().compareTo(o2.getLastName());
            if(cmp==0) {
                cmp = o1.getFirstName().compareTo(o2.getFirstName());
            }
            if(cmp==0) {
                cmp = o1.getRole().name().compareTo(o2.getRole().name());
            }
            if(cmp==0) {
                Integer.compare(o1.getUnitsOfWork(), o2.getUnitsOfWork());
            }
            return cmp;
        });
    }

    @Override
    public String toString() {
        String output = "";
        for(Report r: listOfReports) {
            output += r + "\n";
        }
        return output;
    }
}
