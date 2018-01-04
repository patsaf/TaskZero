package company.scenes;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import company.Display;
import company.employees.Developer;
import company.managers.TeamManager;
import company.windows.AlertBox;
import company.windows.ConfirmExit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeveloperListScene extends Application {

    private Scene scene;
    private TeamManager ceo;
    private TeamManager manager;
    private int index;
    private Label displaymanagerDetails;
    private Button hideTeam;
    private VBox layout;
    private HBox button;
    private final Display display = new Display();
    private ManagerScene managerScene;
    private Button exit;

    public static void main(String[] args) {
        launch(args);
    }

    public DeveloperListScene(TeamManager ceo, int index) {
        this.ceo = ceo;
        this.index = index;
        manager = (TeamManager) ceo.getListEmployee(index);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Welcome to your company!");
        setupLayout();

        hideTeam.setOnAction(e -> {
            managerScene = new ManagerScene(ceo, index);
            try {
                managerScene.start(primaryStage);
            } catch (Exception e1) {
                AlertBox.display("Error loading window");
            }
        });

        exit.setOnAction( e -> {
            if(ConfirmExit.display()) {
                primaryStage.close();
            }
        });

        setupList();
        ScrollPane sp = new ScrollPane();
        sp.setContent(layout);
        sp.setFitToWidth(true);

        scene = new Scene(sp, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupLayout() {
        layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setAlignment(Pos.TOP_CENTER);

        displaymanagerDetails = new Label(display.displayManagerBrief(manager) + "'s team");
        displaymanagerDetails.setStyle("-fx-font-weight: bold");

        button = new HBox(10);
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setPadding(new Insets(10,10,10,10));
        hideTeam = new Button("Return");
        exit = new Button("Exit");
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        button.getChildren().addAll(exit, r, hideTeam);

        layout.getChildren().addAll(displaymanagerDetails);
    }

    private void setupList() {

        VBox listLayout;
        Developer developer;
        Label developerBrief;
        Label developerDetails;

        for(int i=0; i<manager.getListSize(); i++) {
            developer = (Developer) manager.getListEmployee(i);
            developerBrief = new Label(display.displayManagerBrief(developer));
            developerBrief.setStyle("-fx-font-weight: bold");
            developerDetails = new Label(display.displayManager(developer));

            listLayout = new VBox(10);
            listLayout.setAlignment(Pos.TOP_CENTER);
            listLayout.setPadding(new Insets(10,10,10,10));
            listLayout.getChildren().addAll(developerBrief, developerDetails);
            layout.getChildren().addAll(listLayout);
        }
        layout.getChildren().addAll(button);
    }
}
