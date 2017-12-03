package company;

public class Report {

    private final TaskList taskList;

    public Report() {
        taskList = new TaskList();
    }

    public void addReport(Task task) {
        taskList.addTask(task);
    }

    public String toString() {
        return taskList.toString();
    }
}
