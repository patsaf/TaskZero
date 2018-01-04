package company.scenes;

import company.Display;
import company.windows.*;
import company.managers.TeamManager;
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

public class CeoScene extends Application{

    private Scene scene;
    private TeamManager ceo;
    private Label displayCeo;
    private Label displayCeoDetails;
    private Button hire;
    private Button fire;
    private Button assign;
    private Button report;
    private Button showTeam;
    private Button exit;
    private Button startOver;
    private final Display display = new Display();
    private VBox layout;
    private ManagerListScene managerList;
    private CeoScene ceoScene;

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
        setupReport();

        showTeam.setOnAction(e -> {
            managerList = new ManagerListScene(ceo);
            try {
                managerList.start(primaryStage);
            } catch (Exception e1) {
                AlertBox.display("Error loading window");
            }
        });

        startOver.setOnAction(e -> {
            ceo = SetupCompany.display();
            ceoScene = new CeoScene(ceo);
            try {
                ceoScene.start(primaryStage);
            } catch (Exception e1) {
                //AlertBox.display("Error loading window!");
            }
        });

        exit.setOnAction(e -> {
            if(ConfirmExit.display()) {
                primaryStage.close();
            }
        });

        scene = new Scene(layout, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public CeoScene(TeamManager ceo) {
        this.ceo = ceo;
    }

    private void setupLayout() {
        displayCeo = new Label();
        displayCeo.setText(display.displayManagerBrief(ceo));
        displayCeo.setStyle("-fx-font-weight: bold");

        displayCeoDetails = new Label();
        displayCeoDetails.setText(display.displayManager(ceo));

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
        report = new Button("Report");
        buttons.getChildren().addAll(hire, fire, assign, report);

        showTeam = new Button("Show CEO team");
        exit = new Button("Exit");
        startOver = new Button("Start again");

        HBox bottomButtons = new HBox(10);
        bottomButtons.setPadding(new Insets(10,10,10,10));
        bottomButtons.setAlignment(Pos.BASELINE_CENTER);
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        bottomButtons.getChildren().addAll(startOver, r, exit);
        layout.getChildren().addAll(displayCeo, displayCeoDetails, buttons, showTeam, bottomButtons);
    }

    private void setupHire() {
        hire.setOnAction(e -> {
            if(ceo.getListSize() < ceo.getCapacity()) {
                ceo = HireManager.display(ceo);
            } else {
                AlertBox.display("Your team is full!");
            }
        });
    }

    private void setupFire() {
        fire.setOnAction(e -> {
            if(ceo.getListSize()!=0) {
                ceo = FireManager.display(ceo, -1);
            } else {
                AlertBox.display("Your team is empty!");
            }
        });
    }

    private void setupAssign() {
        assign.setOnAction(e -> {
            if(ceo.getListSize()!=0) {
                ceo = Assign.display(ceo, -1);
            } else {
                AlertBox.display("Your team is empty!");
            }
        });
    }

    private void setupReport() {
        report.setOnAction(e -> {
            if(ceo.getListSize()!=0) {
                ReportWindow.display(ceo);
            } else {
                AlertBox.display("Your team is empty!");
            }
        });
    }
}
