package company.windows;

import company.Display;
import company.managers.TeamManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportWindow {

    private static TeamManager ceo;
    private static Stage window;
    private static VBox layout;
    private static Label title;
    private static Label report;
    private static Button back;
    private static Display display = new Display();

    public static void display(TeamManager ceoManager) {
        ceo = ceoManager;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        layout = new VBox(10);
        setupLayout();

        back.setOnAction(e -> window.close() );
        ScrollPane sp = new ScrollPane();
        sp.setContent(layout);
        sp.setFitToWidth(true);

        Scene scene = new Scene(sp, 350, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void setupLayout() {
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        title = new Label("Report gathered by " + display.displayManagerBrief(ceo));
        title.setStyle("-fx-font-weight: bold");
        report = new Label();
        report.setText(ceo.reportWork().toString());

        HBox button = new HBox(10);
        button.setAlignment(Pos.CENTER_RIGHT);
        back = new Button("Return");
        button.getChildren().addAll(back);

        layout.getChildren().addAll(title, report, button);
    }
}
