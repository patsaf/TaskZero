package company.scenes;

import company.Display;
import company.managers.TeamManager;
import company.windows.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerScene extends Application {

    private Scene scene;
    private TeamManager ceo;
    private final int index;
    private TeamManager manager;
    private Label displayManager;
    private Label displayManagerDetails;
    private Button hire;
    private Button fire;
    private Button assign;
    private Button showTeam;
    private Button back;
    private Button exit;
    private final Display display = new Display();
    private VBox layout;
    private ManagerListScene managerList;
    private DeveloperListScene developerList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Welcome to your company!");
        setupLayout();
        setupHire();
        setupFire();
        setupAssign();

        back.setOnAction(e -> {
            managerList = new ManagerListScene(ceo);
            try {
                managerList.start(primaryStage);
            } catch (Exception e1) {
                AlertBox.display("Error loading window");
            }
        });

        exit.setOnAction( e -> {
            if(ConfirmExit.display()) {
                primaryStage.close();
            }
        });

        showTeam.setOnAction(e -> {
            developerList = new DeveloperListScene(ceo, index);
            try {
                developerList.start(primaryStage);
            } catch (Exception e1) {
                AlertBox.display("Error loading window");
            }
        });

        scene = new Scene(layout, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public ManagerScene(TeamManager ceo, int index) {
        this.ceo = ceo;
        this.index = index;
        manager = (TeamManager) ceo.getListEmployee(index);
    }

    private void setupLayout() {
        displayManager = new Label();
        displayManager.setText(display.displayManagerBrief(manager));
        displayManager.setStyle("-fx-font-weight: bold");

        displayManagerDetails = new Label();
        displayManagerDetails.setText(display.displayManager(manager));

        layout = new VBox();
        layout.setSpacing(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.TOP_CENTER);
        hire = new Button("Hire");
        fire = new Button("Fire");
        assign = new Button("Assign");
        buttons.getChildren().addAll(hire, fire, assign);

        showTeam = new Button("Show manager team");
        exit = new Button("Exit");
        back = new Button("Return");
        HBox bottomButtons = new HBox();
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        bottomButtons.getChildren().addAll(exit, r, back);
        layout.getChildren().addAll(displayManager, displayManagerDetails, buttons, showTeam, bottomButtons);
    }

    private void setupHire() {
        hire.setOnAction(e -> {
            if(manager.getListSize() < manager.getCapacity()) {
                ceo = HireDeveloper.display(ceo, index);
            } else {
                AlertBox.display("Your team is full!");
            }
        });
    }

    private void setupFire() {
        fire.setOnAction(e -> {
            if(manager.getListSize()!=0) {
                ceo = FireManager.display(ceo, index);
            } else {
                AlertBox.display("Your team is empty!");
            }
        });
    }

    private void setupAssign() {
        assign.setOnAction(e -> {
            if(manager.getListSize()!=0) {
                ceo = Assign.display(ceo, index);
            } else {
                AlertBox.display("Your team is empty!");
            }
        });
    }
}
