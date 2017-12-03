package company;

public class Task {

    private final String taskName;
    private final int unitsOfWork;

    public Task(String taskName, int unitsOfWork) {
        this.taskName = taskName;
        this.unitsOfWork = unitsOfWork;
    }

    public String getTask() {
        return taskName;
    }

    public int getUnitsOfWork() { return unitsOfWork; }

}
