package company.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmExit {

    private static boolean answer;

    public static boolean display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ERROR");

        Label label = new Label();
        label.setText("Are you sure you want to exit?");
        Button yes = new Button("YES");
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button no = new Button("NO");
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10, 10, 10,10));
        buttons.getChildren().addAll(yes, no);

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10,10));
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
