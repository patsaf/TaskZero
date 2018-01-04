package company.scenes;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import company.Display;
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

public class ManagerListScene extends Application {

    private Scene scene;
    private TeamManager ceo;
    private Label displayCeoDetails;
    private Button hideTeam;
    private Button exit;
    private VBox layout;
    private HBox button;
    private CeoScene ceoScene;
    private final Display display = new Display();
    private ManagerScene managerScene;

    public static void main(String[] args) {
        launch(args);
    }

    public ManagerListScene(TeamManager ceo) { this.ceo = ceo;   }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Welcome to your company!");
        setupLayout();

        hideTeam.setOnAction(e -> {
            ceoScene = new CeoScene(ceo);
            try {
                ceoScene.start(primaryStage);
            } catch (Exception e1) {
                AlertBox.display("Error loading window");
            }
        });

        exit.setOnAction( e -> {
            if(ConfirmExit.display()) {
                primaryStage.close();
            }
        });

        setupList(primaryStage);
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

        displayCeoDetails = new Label(display.displayManagerBrief(ceo) + "'s team");
        displayCeoDetails.setStyle("-fx-font-weight: bold");

        button = new HBox(10);
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setPadding(new Insets(10,10,10,10));
        hideTeam = new Button("Return");
        exit = new Button("Exit");
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        button.getChildren().addAll(exit, r, hideTeam);

        layout.getChildren().addAll(displayCeoDetails);
    }

    private void setupList(Stage stage) {

        VBox listLayout;

        TeamManager manager;
        Label managerBrief;
        Label managerDetails;
        Button managerSettings;

        for(int i=0; i<ceo.getListSize(); i++) {
            manager = (TeamManager) ceo.getListEmployee(i);
            managerBrief = new Label(display.displayManagerBrief(manager));
            managerBrief.setStyle("-fx-font-weight: bold");
            managerDetails = new Label(display.displayManager(manager));
            managerSettings = new Button("Show manager options");

            int index = i;
            managerSettings.setOnAction(e -> {
                managerScene = new ManagerScene(ceo, index);
                try {
                    managerScene.start(stage);
                } catch (Exception e1) {
                    AlertBox.display("Error loading window");
                }
            });

            listLayout = new VBox(10);
            listLayout.setAlignment(Pos.TOP_CENTER);
            listLayout.setPadding(new Insets(10,10,10,10));
            listLayout.getChildren().addAll(managerBrief, managerDetails, managerSettings);
            layout.getChildren().addAll(listLayout);
        }
        layout.getChildren().addAll(button);
    }

}
