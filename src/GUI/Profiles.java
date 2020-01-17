package GUI;

import Controllers.ProfileController;
import Database.ProfileDAO;
import Database.ProgramDAO;
import Database.SerieDAO;
import Domain.*;
import com.sun.javafx.image.IntPixelGetter;
import javafx.scene.Scene;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Profiles {

    /**
     * Returns a scene with a list of all profiles.
     * @param stage
     * @param email
     * @return list with all the profiles.
     */
    public Scene profileList(Stage stage, String email){
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.prefWidth(1600.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1160);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setStyle("-fx-background: #383838;");

        ProfileDAO profileDAO = new ProfileDAO();
        ArrayList<Profile> profileList = profileDAO.getAllProfiles(email);


        int count = 0;
        for (Profile profile: profileList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblName = new Label("Name: " + profile.getName());
            lblName.getStyleClass().add("headLabels");
            lblName.setLayoutX(65);
            lblName.setLayoutY(14);

            Label lblAge = new Label("Age: " +(profile.getAge()));
            lblAge.getStyleClass().add("labels");
            lblAge.setLayoutY(37);
            lblAge.setLayoutX(65);


            Button btnEdit = new Button("Edit");
            btnEdit.setLayoutX(1089);
            btnEdit.setLayoutY(55);
            btnEdit.setMinWidth(80);
            btnEdit.getStyleClass().add("buttons");
            btnEdit.setOnAction(e -> stage.setScene(new Profiles().editProfile(stage, profile)));

            Button btnDelete = new Button("Delete");
            btnDelete.setLayoutX(1089);
            btnDelete.setLayoutY(13);
            btnDelete.setMinWidth(80);
            btnDelete.getStyleClass().add("buttons");
            btnDelete.setId("btnDelete");
            ProfileController controller = new ProfileController(stage, profile);
            btnDelete.setOnAction(controller);


            Button btnWatch = new Button("Watch program");
            btnWatch.setLayoutX(910);
            btnWatch.setLayoutY(13);
            btnWatch.setMinWidth(142);
            btnWatch.getStyleClass().add("buttons");
            btnWatch.setOnAction(e -> stage.setScene(new WatchedGUI().watchMovie(stage, email, profile)));

            Button btnWatchedPreviously = new Button("Watched programs");
            btnWatchedPreviously.setLayoutX(910);
            btnWatchedPreviously.setLayoutY(55);
            btnWatchedPreviously.setMinWidth(142);
            btnWatchedPreviously.getStyleClass().add("buttons");
            btnWatchedPreviously.setOnAction(e -> stage.setScene(new WatchedGUI().watchedList(stage, profile.getProfileId(), email, profile.getName())));

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255,255,255));

            accountPane.getChildren().addAll(lblName, lblAge);
            accountPane.getChildren().addAll(btnEdit, btnDelete, btnWatch, btnWatchedPreviously );
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }

        Label lblPageTitle = new Label("Profile overview of " + email);
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        Button btnAddNewProfile = new Button("New Profile");
        btnAddNewProfile.getStyleClass().add("buttons");
        btnAddNewProfile.getStyleClass().add("newButton");
        btnAddNewProfile.setLayoutY(2);
        btnAddNewProfile.setLayoutX(1457);
        btnAddNewProfile.setOnAction(e -> stage.setScene(new Profiles().addProfile(stage, email)));

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle, scrollPane, btnAddNewProfile);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }


    /**
     * A scene to edit the profile
     * @param stage
     * @param profile the profile to edit
     * @return scene
     */
    public Scene editProfile(Stage stage, Profile profile) {
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.setMinWidth(900.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1160);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        Label lblName = new Label("Name profile");
        lblName.setLayoutX(465);
        lblName.setLayoutY(53);
        lblName.getStyleClass().add("whiteLabels");

        Label lblAge = new Label("Age");
        lblAge.setLayoutX(465);
        lblAge.setLayoutY(93);
        lblAge.getStyleClass().add("whiteLabels");

        TextField txtName = new TextField(profile.getName());
        txtName.setLayoutX(666);
        txtName.setLayoutY(53);

        TextField txtAge = new TextField(Integer.toString(profile.getAge()));
        txtAge.setLayoutX(666);
        txtAge.setLayoutY(93);

        ProfileController controller = new ProfileController(stage, profile);
        controller.setTxtAgeProfile(txtAge);
        controller.setTxtNameProfile(txtName);
        controller.setEmail(profile.getEmail());

        Button btnSubmit = new Button("Submit changes");
        btnSubmit.setId("btnSubmit");
        btnSubmit.setLayoutX(666);
        btnSubmit.setMinWidth(149);
        btnSubmit.setLayoutY(138);
        btnSubmit.getStyleClass().add("buttons");
        btnSubmit.setOnAction(controller);

        Label lblPageTitle = new Label("Edit profile");
        lblPageTitle.setLayoutX(465);
        lblPageTitle.getStyleClass().add("lblEditTitle");

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle, lblAge, lblName, txtName, txtAge, btnSubmit);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }


    /**
     * Add profile scene
     * @param stage
     * @param email the email of the account to attach the profile to
     * @return a scene
     */
    public Scene addProfile(Stage stage, String email) {
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.setMinWidth(900.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1160);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        Label lblName = new Label("Name profile");
        lblName.setLayoutX(465);
        lblName.setLayoutY(53);
        lblName.getStyleClass().add("whiteLabels");

        Label lblAge = new Label("Age");
        lblAge.setLayoutX(465);
        lblAge.setLayoutY(93);
        lblAge.getStyleClass().add("whiteLabels");

        TextField txtName = new TextField();
        txtName.setLayoutX(666);
        txtName.setLayoutY(53);

        TextField txtAge = new TextField();
        txtAge.setLayoutX(666);
        txtAge.setLayoutY(93);

        ProfileController controller = new ProfileController(stage);
        controller.setTxtNameProfile(txtName);
        controller.setTxtAgeProfile(txtAge);
        controller.setEmail(email);

        Button btnAdd = new Button("Add profile");
        btnAdd.setId("btnAdd");
        btnAdd.setLayoutX(666);
        btnAdd.setMinWidth(149);
        btnAdd.setLayoutY(138);
        btnAdd.getStyleClass().add("buttons");
        btnAdd.setOnAction(controller);

        Label lblPageTitle = new Label("Add profile");
        lblPageTitle.setLayoutX(465);
        lblPageTitle.getStyleClass().add("lblEditTitle");

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle, lblAge, lblName, txtName, txtAge, btnAdd);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}
