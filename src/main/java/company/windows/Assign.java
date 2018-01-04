package company.windows;

import company.Display;
import company.managers.TeamManager;
import company.tasks.Task;
import company.tasks.TaskFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Assign {

    private static TeamManager ceo;
    private static TeamManager manager;
    private static int index;
    private static ChoiceBox<String> employeeList;
    private static Label title;
    private static Label taskNameLabel;
    private static TextField taskName;
    private static Label unitsLabel;
    private static TextField units;
    private static Button assign;
    private static Button back;
    private static Button generateRandom;
    private static VBox layout;
    private static Stage window;
    private static Display display = new Display();
    private static ArrayList<String> list;
    private static GridPane hiddenForm;
    private static HBox buttons;

    public static TeamManager display(TeamManager ceoManager, int managerIndex) {
        ceo = ceoManager;
        index = managerIndex;
        if(index!=-1) {
            manager = (TeamManager) ceo.getListEmployee(index);
        }
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Assign tasks");
        setupLayout();
        setupSpinner();
        setupButtons();

        Scene scene = new Scene(layout, 350, 400);
        window.setScene(scene);
        window.showAndWait();
        return ceo;
    }

    private static void setupLayout() {
        layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        list = new ArrayList<>();
        list.add(0, "[select employee]");
        if(index!=-1) {
            title = new Label("Tasks assigned by: " + display.displayManagerBrief(manager));
            for(int i=0; i<manager.getListSize(); i++) {
                list.add(i+1,display.displayManagerBrief(manager.getListEmployee(i)));
            }

        } else {
            title = new Label("Tasks assigned by: " + display.displayManagerBrief(ceo));
            for(int i=0; i<ceo.getListSize(); i++) {
                list.add(i+1,display.displayManagerBrief(ceo.getListEmployee(i)));
            }
        }
        employeeList = new ChoiceBox<>();
        employeeList.getItems().clear();
        employeeList.getItems().addAll(list);
        employeeList.setValue(list.get(0));

        hiddenForm = new GridPane();
        hiddenForm.setPadding(new Insets(10, 10, 10, 10));
        hiddenForm.setVgap(10);
        hiddenForm.setHgap(10);
        taskNameLabel = new Label("Insert task description:");
        taskName = new TextField();
        taskName.setPromptText("Task description");
        unitsLabel = new Label("Units of work");
        units = new TextField();
        units.setPromptText("Units of work");
        GridPane.setConstraints(taskNameLabel, 0, 0);
        GridPane.setConstraints(taskName, 1, 0);
        GridPane.setConstraints(unitsLabel, 0, 1);
        GridPane.setConstraints(units, 1, 1);
        hiddenForm.getChildren().addAll(taskNameLabel, taskName, unitsLabel, units);

        buttons = new HBox(10);
        assign = new Button("Assign");
        generateRandom = new Button("Generate random details");
        back = new Button("Done");
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        buttons.getChildren().addAll(generateRandom, r, assign);

        HBox doneButton = new HBox(10);
        doneButton.setAlignment(Pos.CENTER_RIGHT);
        doneButton.getChildren().addAll(back);

        hiddenForm.setVisible(false);
        buttons.setVisible(false);

        layout.getChildren().addAll(title, employeeList, hiddenForm, buttons, doneButton);
    }

    private static void setupSpinner() {
        employeeList.setOnAction(e -> {
            if(!employeeList.getValue().equals(list.get(0))) {
                hiddenForm.setVisible(true);
                buttons.setVisible(true);
            } else {
                hiddenForm.setVisible(false);
                buttons.setVisible(false);
            }
        });
    }

    private static void setupButtons() {
        back.setOnAction(e -> window.close());

        generateRandom.setOnAction(e -> {
            TaskFactory taskItem = new TaskFactory();
            Random r = new Random();
            taskName.setText(taskItem.getTaskName());
            units.setText(String.valueOf(r.nextInt(20)+1));
        });

        assign.setOnAction(e -> {
            Task task;
            setTextBlack();
            if(!employeeList.getValue().equals(list.get(0))) {
                if (checkForm()) {
                    try {
                        task = new Task(taskName.getText(), Integer.parseInt(units.getText()));
                        int i = list.indexOf(employeeList.getValue());
                        if(index!=-1) {
                            manager.assign(task, manager.getListEmployee(i - 1));

                        } else {
                            ceo.assign(task, ceo.getListEmployee(i - 1));
                        }
                        employeeList.setValue(list.get(0));
                        taskName.clear();
                        units.clear();
                    } catch (IllegalArgumentException iae) {
                        AlertBox.display("Invalid data!");
                    }
                }
            }
        });
    }

    private static void setTextBlack() {
        taskNameLabel.setStyle("-fx-text-fill: black");
        unitsLabel.setStyle("-fx-text-fill: black");
    }

    private static boolean checkForm() {
        boolean canAssign = true;
        if(taskName.getText().equals("")) {
            canAssign = false;
            taskNameLabel.setStyle("-fx-text-fill: red");
        }
        if(units.getText().equals("")) {
            canAssign = false;
            unitsLabel.setStyle("-fx-text-fill: red");
        }
        return canAssign;
    }
}
