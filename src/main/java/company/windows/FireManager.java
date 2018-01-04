package company.windows;

import company.Display;
import company.employees.Developer;
import company.managers.TeamManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FireManager {

    private static TeamManager ceo;
    private static TeamManager manager;
    private static int index;
    private static Developer developer;
    private static Stage window;
    private static VBox layout;
    private static ChoiceBox<String> employeeList;
    private static Display display = new Display();
    private static ArrayList<String> list;
    private static Label title;

    public static TeamManager display(TeamManager ceoManager, int managerIndex) {
        ceo = ceoManager;
        index = managerIndex;
        if(index!=-1) {
            manager = (TeamManager) ceo.getListEmployee(index);
        }
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Fire managers");
        list = new ArrayList<>();
        layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        if(index!=-1) {
            title = new Label("Choose developers to fire");
        } else {
            title = new Label("Choose managers to fire");
        }
        employeeList = new ChoiceBox<>();
        setListAdapter();
        Button fire = new Button("Fire");

        fire.setOnAction(e -> {
            if(!employeeList.getValue().equals(list.get(0))) {
                int i = list.indexOf(employeeList.getValue());
                if(index!=-1) {
                    developer = (Developer) manager.getListEmployee(i-1);
                    manager.fire(manager.getListEmployee(i-1));
                } else {
                    manager = (TeamManager) ceo.getListEmployee(i-1);
                    ceo.fire(ceo.getListEmployee(i - 1));
                }
                setListAdapter();
            } else {
                AlertBox.display("Select a manager!");
            }
        });

        Button back = new Button("Done");
        back.setOnAction(e -> window.close() );

        Button undo = new Button("Undo");
        undo.setOnAction( e -> {
            if(index!=-1) {
                manager.hire(developer);
            } else {
                ceo.hire(manager);
            }
            setListAdapter();
        });

        VBox fireButton = new VBox(10);
        fireButton.setAlignment(Pos.CENTER_RIGHT);
        fireButton.getChildren().addAll(fire);
        fireButton.setPadding(new Insets(10, 10, 0, 10));

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(0, 10, 10, 10));
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        buttons.getChildren().addAll(undo, r, back);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(title, employeeList, fireButton, buttons);

        Scene scene = new Scene(layout, 350, 170);
        window.setScene(scene);
        window.showAndWait();
        return ceo;
    }

    private static void setListAdapter() {
        list.clear();
        list.add(0, "[select employee]");
        if(index!=-1) {
            for (int i = 0; i < manager.getListSize(); i++) {
                list.add(i + 1, display.displayManagerBrief(manager.getListEmployee(i)));
            }
        } else {
            for (int i = 0; i < ceo.getListSize(); i++) {
                list.add(i + 1, display.displayManagerBrief(ceo.getListEmployee(i)));
            }
        }
        employeeList.getItems().clear();
        employeeList.getItems().addAll(list);
        employeeList.setValue(list.get(0));
    }

}
