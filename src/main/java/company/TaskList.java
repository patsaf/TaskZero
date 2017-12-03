package company;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public Task getTask(int i) {
        return listOfTasks.get(i);
    }

    public String toString() {
        String output = "";
        for(Task t: listOfTasks) {
            output += t.getTask() + ": " + t.getUnitsOfWork() + " units of work\n";
        }
        return output;
    }
}
