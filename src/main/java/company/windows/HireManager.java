package company.windows;

import company.employees.EmployeeFactory;
import company.employees.details.*;
import company.managers.TeamManager;
import company.predicates.PredicateInfo;
import company.predicates.Predicates;
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

public class HireManager {

    private static final String[] genders = {"Female", "Male"};
    private static final String[] conditions = {"No condition", "University", "Country", "Gender", "Email"};

    private static Stage window;
    private static TeamManager ceo;
    private static TeamManager manager;
    private static Gender genderItem;
    private static Gender genderConditionItem;
    private static Predicates predicateItem;

    private static BorderPane border;
    private static Button hire;
    private static Button generateRandom;
    private static Button cancel;
    private static ChoiceBox<String> gender;
    private static ChoiceBox<String> condition;
    private static ChoiceBox<String> conditionDetailSpinner;
    private static Label conditionLabel;
    private static TextField conditionDetail;
    private static Label conditionDetailLabel;
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
    private static Label capacityLabel;
    private static TextField capacity;

    public static TeamManager display(TeamManager ceoManager) {

        ceo = ceoManager;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hire manager");
        predicateItem = Predicates.EMPTY;
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
        Label title = new Label("Fill your manager details");
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
        capacityLabel = new Label("Capacity:");
        capacity = new TextField();
        capacity.setPromptText("Capacity");
        conditionLabel = new Label("Hires only:");
        condition = new ChoiceBox<>();
        condition.getItems().addAll(conditions);
        condition.setValue(conditions[0]);
        predicateItem = Predicates.EMPTY;
        genderConditionItem = Gender.FEMALE;

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10,10,10,10));
        conditionDetailLabel = new Label("Insert condition:");
        conditionDetailSpinner = new ChoiceBox<>();
        conditionDetailSpinner.getItems().addAll(genders);
        conditionDetailSpinner.setValue(genders[0]);
        conditionDetail = new TextField();
        conditionDetail.setPromptText("Condition detail");
        conditionDetailLabel.setVisible(false);
        conditionDetail.setVisible(false);
        conditionDetailSpinner.setVisible(false);

        GridPane.setConstraints(firstNameLabel, 0, 0);
        GridPane.setConstraints(firstName, 1, 0);
        GridPane.setConstraints(lastNameLabel, 0, 1);
        GridPane.setConstraints(lastName, 1, 1);
        GridPane.setConstraints(genderLabel, 0, 2);
        GridPane.setConstraints(gender, 1, 2);
        GridPane.setConstraints(emailLabel, 0, 3);
        GridPane.setConstraints(email, 1, 3);
        GridPane.setConstraints(universityLabel, 0, 4);
        GridPane.setConstraints(university, 1, 4);
        GridPane.setConstraints(countryLabel, 0, 5);
        GridPane.setConstraints(country, 1, 5);
        GridPane.setConstraints(capacityLabel, 0, 6);
        GridPane.setConstraints(capacity, 1, 6);
        GridPane.setConstraints(conditionLabel, 0, 7);
        GridPane.setConstraints(condition, 1, 7);
        GridPane.setConstraints(conditionDetailLabel, 0, 8);
        GridPane.setConstraints(conditionDetail, 1, 8);
        GridPane.setConstraints(conditionDetailSpinner, 1, 8);
        form.getChildren().addAll(conditionDetailLabel, conditionDetail, conditionDetailSpinner, firstNameLabel, firstName,
                lastNameLabel, lastName, genderLabel, gender, emailLabel, email, universityLabel, university, countryLabel, country,
                capacityLabel, capacity, conditionLabel, condition);
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

        condition.setOnAction(e -> {
            if(!condition.getValue().equals(conditions[0])) {
                conditionDetailLabel.setVisible(true);
                if (condition.getValue().equals("Gender")) {
                    conditionDetailSpinner.setVisible(true);
                    conditionDetail.setVisible(false);
                    predicateItem = Predicates.GENDER;
                    conditionDetailSpinner.setOnAction(event -> {
                        if(conditionDetailSpinner.getValue().equals(genders[0])) {
                            genderConditionItem = Gender.FEMALE;
                        } else {
                            genderConditionItem = Gender.MALE;
                        }
                    });
                } else {
                    conditionDetail.setVisible(true);
                    conditionDetailSpinner.setVisible(false);
                    if(condition.getValue().equals("University")) {
                        predicateItem = Predicates.UNIVERSITY;
                    } else if (condition.getValue().equals("Country")) {
                        predicateItem = Predicates.COUNTRY;
                    } else if (condition.getValue().equals("Email")) {
                        predicateItem = Predicates.EMAIL;
                    }
                }
            } else {
                conditionDetailLabel.setVisible(false);
                conditionDetail.setVisible(false);
                conditionDetailSpinner.setVisible(false);
                predicateItem = Predicates.EMPTY;
            }
        });
    }

    private static void setupGenerateRandom() {
        generateRandom.setOnAction(e -> {
            EmployeeFactory employeeFactory = new EmployeeFactory(EmployeeType.MANAGER, EmployeeRole.CEO, 2);
            Random r = new Random();
            lastName.setText(employeeFactory.getLastName());
            email.setText(employeeFactory.getEmail());
            university.setText(employeeFactory.getUniversity());
            country.setText(employeeFactory.getCountry());
            capacity.setText(String.valueOf(r.nextInt(5)+1));
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
                    manager = new TeamManager.ManagerBuilder(EmployeeRole.DEVELOPMENT_MANAGER)
                            .name(new FirstName(firstName.getText()), new LastName(lastName.getText()))
                            .capacity(Integer.parseInt(capacity.getText()))
                            .country(new Country(country.getText()))
                            .university(new University(university.getText()))
                            .email(new Email(email.getText()))
                            .gender(genderItem)
                            .hiringCondition(new PredicateInfo(predicateItem,
                                    (predicateItem == Predicates.GENDER) ?
                                            genderConditionItem.name() : conditionDetail.getText().toString()))
                            .build();
                    if(!ceo.makePredicate().test(manager)) {
                        AlertBox.display(ceo.getConditionInfo().toString());
                    } else {
                        ceo.hire(manager);
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
        capacityLabel.setStyle("-fx-text-fill: black");
        emailLabel.setStyle("-fx-text-fill: black");
        conditionDetailLabel.setStyle("-fx-text-fill: black");
    }

    private static boolean checkForm() {
        boolean canHire = true;
        if(firstName.getText().equals("")) {
            firstNameLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        } if(lastName.getText().equals("")) {
            lastNameLabel.setStyle("-fx-text-fill: red");
            canHire = false;
        } if(capacity.getText().equals("")) {
            capacityLabel.setStyle("-fx-text-fill: red");
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
        } if(predicateItem!=Predicates.EMPTY && predicateItem!=Predicates.GENDER) {
            if(conditionDetail.getText().equals("")) {
                conditionDetailLabel.setStyle("-fx-text-fill: red");
                canHire = false;
            }
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
