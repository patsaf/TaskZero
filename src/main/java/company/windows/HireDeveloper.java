package company.windows;

import company.employees.Developer;
import company.employees.EmployeeFactory;
import company.employees.details.*;
import company.managers.TeamManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;

public class HireDeveloper {

    private static final String[] genders = {"Female", "Male"};
    private static final String[] roles = {"Developer", "Tester", "Team leader", "Contributor"};

    private static Stage window;
    private static TeamManager ceo;
    private static TeamManager manager;
    private static Developer developer;
    private static int index;
    private static Gender genderItem;
    private static EmployeeRole roleItem;

    private static BorderPane border;
    private static Button hire;
    private static Button generateRandom;
    private static Button cancel;
    private static ChoiceBox<String> gender;
    private static Label firstNameLabel;
    private static TextField firstName;
    private static Label lastNameLabel;
    private static TextField lastName;
    private static Label genderLabel;
    private static Label emailLabel;
    private static TextField email;
    private static Label universityLabel;
    private static TextField university;
    private static Label countryLabel;
    private static TextField country;
    private static Label roleLabel;
    private static ChoiceBox<String> role;

    public static TeamManager display(TeamManager ceoManager, int managerIndex) {

        ceo = ceoManager;
        index = managerIndex;
        manager = (TeamManager) ceo.getListEmployee(index);
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hire developer");
        setupLayout();
        setupForm();
        setupGenerateRandom();
        setupHire();

        cancel.setOnAction( e -> window.close() );

        Scene scene = new Scene(border, 350, 400);
        window.setScene(scene);
        window.showAndWait();

        return ceo;
    }

    private static void setupLayout() {
        border = new BorderPane();
        border.setPadding(new Insets(10,10,10,10));

        StackPane top = new StackPane();
        Label title = new Label("Fill your developer details");
        top.getChildren().addAll(title);
        border.setTop(top);

        firstNameLabel = new Label("First name:");
        firstName = new TextField();
        firstName.setPromptText("First name");
        lastNameLabel = new Label("Last name:");
        lastName = new TextField();
        lastName.setPromptText("Last name");
        genderLabel = new Label("Gender:");
        gender = new ChoiceBox<>();
        gender.getItems().addAll(genders);
        gender.setValue(genders[0]);
        genderItem = Gender.FEMALE;
        emailLabel = new Label("Email:");
        email = new TextField();
        email.setPromptText("Email");
        universityLabel = new Label("University:");
        university = new TextField();
        university.setPromptText("University");
        countryLabel = new Label("Country:");
        country = new TextField();
        country.setPromptText("Country");
        roleLabel = new Label("Role:");
        role = new ChoiceBox<>();
        role.getItems().addAll(roles);
        role.setValue(roles[0]);
        roleItem = EmployeeRole.DEVELOPER;

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10,10,10,10));

        GridPane.setConstraints(roleLabel, 0, 0);
        GridPane.setConstraints(role, 1, 0);
        GridPane.setConstraints(firstNameLabel, 0, 1);
        GridPane.setConstraints(firstName, 1, 1);
        GridPane.setConstraints(lastNameLabel, 0, 2);
        GridPane.setConstraints(lastName, 1, 2);
        GridPane.setConstraints(genderLabel, 0, 3);
        GridPane.setConstraints(gender, 1, 3);
        GridPane.setConstraints(emailLabel, 0, 4);
        GridPane.setConstraints(email, 1, 4);
        GridPane.setConstraints(universityLabel, 0, 5);
        GridPane.setConstraints(university, 1, 5);
        GridPane.setConstraints(countryLabel, 0, 6);
        GridPane.setConstraints(country, 1, 6);
        form.getChildren().addAll(roleLabel, role, firstNameLabel, firstName, lastNameLabel, lastName, genderLabel, gender, emailLabel,
                email, universityLabel, university, countryLabel, country);
        border.setCenter(form);

        HBox bottomButtons = new HBox();
        generateRandom = new Button("Generate random details");
        hire = new Button("Hire");
        cancel = new Button("Cancel");
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        Region r1 = new Region();
        HBox.setHgrow(r1, Priority.ALWAYS);
        bottomButtons.getChildren().addAll(cancel, r1, generateRandom, r, hire);
        border.setBottom(bottomButtons);
    }

    private static void setupForm() {
        gender.setOnAction(e -> {
            if(gender.getValue().equals(genders[0])) {
                genderItem = Gender.FEMALE;
            } else {
                genderItem = Gender.MALE;
            }
        });

        role.setOnAction(e -> {
            if(role.getValue().equals(roles[0])) {
                roleItem = EmployeeRole.DEVELOPER;
            } else if(role.getValue().equals(roles[1])) {
                roleItem = EmployeeRole.TESTER;
            } else if(role.getValue().equals(roles[2])) {
                roleItem = EmployeeRole.TEAM_LEADER;
            } else {
                roleItem = EmployeeRole.CONTRIBUTOR;
            }
        });
    }

    private static void setupGenerateRandom() {
        generateRandom.setOnAction(e -> {
            EmployeeFactory employeeFactory = new EmployeeFactory(EmployeeType.DEVELOPER, roleItem, 2);
            Random r = new Random();
            lastName.setText(employeeFactory.getLastName());
            email.setText(employeeFactory.getEmail());
            university.setText(employeeFactory.getUniversity());
            country.setText(employeeFactory.getCountry());
            firstName.setText(gender.getValue().equals(genders[0]) ? employeeFactory.getFemaleName() : employeeFactory.getMaleName());
        });
    }

    private static void setupHire() {

        hire.setOnAction(e -> {
            setTextBlack();
            if(checkForm()) {
                try {
                    setUpperCase(firstName);
                    setUpperCase(lastName);
                    setUpperCase(country);
                    setUpperCase(university);
                    developer = (Developer) new Developer.DeveloperBuilder(roleItem)
                            .name(new FirstName(firstName.getText()), new LastName(lastName.getText()))
                            .country(new Country(country.getText()))
                            .university(new University(university.getText()))
                            .email(new Email(email.getText()))
                            .gender(genderItem)
                            .build();
                    if(!manager.makePredicate().test(developer)) {
                        AlertBox.display(manager.getConditionInfo().toString());
                    } else {
                        manager.hire(developer);
                        window.close();
                    }
                } catch(IllegalArgumentException iae) {
                    AlertBox.display("Invalid data!");
                }
            }
        });
    }

    private static void setTextBlack() {
        firstNameLabel.setStyle("-fx-text-fill: black");
        lastNameLabel.setStyle("-fx-text-fill: black");
        universityLabel.setStyle("-fx-text-fill: black");
        countryLabel.setStyle("-fx-text-fill: black");
        emailLabel.setStyle("-fx-text-fill: black");
    }

    private static boolean checkForm() {
        boolean canHire = true;
        if(firstName.getText().equals("")) {
            firstNameLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        } if(lastName.getText().equals("")) {
            lastNameLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        } if(email.getText().equals("")) {
            emailLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        } if(university.getText().equals("")) {
            universityLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        } if(country.getText().equals("")) {
            countryLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        }
        return canHire;
    }

    private static void setUpperCase(TextField field) {
        String content = field.getText();
        StringBuilder sb = new StringBuilder(content);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        content = sb.toString();
        field.setText(content);
    }
}
