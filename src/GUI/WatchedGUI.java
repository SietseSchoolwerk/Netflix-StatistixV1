package GUI;

import Controllers.ProfileController;
import Database.ProfileDAO;
import Database.ProgramDAO;
import Database.SerieDAO;
import Domain.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WatchedGUI {


    public Scene watchMovie(Stage stage, String email, Profile profile) {
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.prefWidth(1600.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1140);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setStyle("-fx-background: #383838;");

        ProgramDAO programDAO = new ProgramDAO();
        ArrayList<Program> programList = programDAO.getAllPrograms();


        int count = 0;
        for (Program program: programList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblTitel = new Label(program.getTitle());
            lblTitel.getStyleClass().add("accountEmail");
            lblTitel.setLayoutX(65);
            lblTitel.setLayoutY(14);

            Label lblGenre = new Label((program.getGenre()));
            lblGenre.getStyleClass().add("accountLabels");
            lblGenre.setLayoutY(37);
            lblGenre.setLayoutX(65);

            Label lblLan = new Label(program.getLanguage());
            lblLan.getStyleClass().add("accountLabels");
            lblLan.setLayoutY(54);
            lblLan.setLayoutX(65);

            Label lblAge = new Label(Integer.toString(program.getAge()));
            lblAge.getStyleClass().add("accountLabels");
            lblAge.setLayoutY(73);
            lblAge.setLayoutX(65);

            Label lblPlaytimeInfo = new Label("Playtime in minutes: ");
            lblPlaytimeInfo.getStyleClass().add("accountLabels");
            lblPlaytimeInfo.setLayoutY(54);
            lblPlaytimeInfo.setLayoutX(253);

            Label lblPlaytime = new Label(program.getLengthOfTime());
            lblPlaytime.getStyleClass().add("accountLabels");
            lblPlaytime.setLayoutY(54);
            lblPlaytime.setLayoutX(390);

            if (new ProgramDAO().isSerie(program.getProgramId())) {
                Episode episode = (Episode)program;

                Serie serie = new SerieDAO().getSerie(program.getProgramId());

                Label lblSerieTitle = new Label(serie.getTitle());
                lblSerieTitle.getStyleClass().add("accountLabels");
                lblSerieTitle.setLayoutY(14);
                lblSerieTitle.setLayoutX(253);

                Label lblVolume = new Label(episode.getFollowNumber());
                lblVolume.getStyleClass().add("accountLabels");
                lblVolume.setLayoutY(73);
                lblVolume.setLayoutX(253);
                accountPane.getChildren().addAll(lblSerieTitle, lblVolume);
            }


            Button btnWatch = new Button("Watch");
            btnWatch.setLayoutX(1089);
            btnWatch.setLayoutY(13);
            btnWatch.setMinWidth(80);
            btnWatch.getStyleClass().add("accountButtons");
            ProfileController controller = new ProfileController(stage);
            controller.setProfile(profile);
            controller.setEmail(email);
            controller.setProgramId(program.getProgramId());
            btnWatch.setOnAction(e -> stage.setScene(new WatchedGUI().addWatched(stage, controller)));



            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255,255,255));

            accountPane.getChildren().addAll(lblTitel, lblGenre, lblLan, lblAge, lblPlaytime, lblPlaytimeInfo);
            accountPane.getChildren().addAll(btnWatch);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage),scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }


    public Scene addWatched(Stage stage, ProfileController controller) {
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.setMinWidth(900.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1140);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        Label lblPercentage = new Label("Watched percentage");
        lblPercentage.setLayoutX(465);
        lblPercentage.setLayoutY(53);
        lblPercentage.getStyleClass().add("whiteLabels");


        TextField txtPercentage = new TextField();
        txtPercentage.setLayoutX(666);
        txtPercentage.setLayoutY(53);

        controller.setTxtPercentage(txtPercentage);


        Button btnWatch = new Button("Submit changes");
        btnWatch.setId("btnWatch");
        btnWatch.setLayoutX(465);
        btnWatch.setLayoutY(138);
        btnWatch.getStyleClass().add("accountButtons");
        btnWatch.setOnAction(controller);

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPercentage, txtPercentage, btnWatch);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    public Scene watchedList(Stage stage, int profileId, String email) {
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.prefWidth(1600.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1160);
        scrollPane.setMinHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        ProfileDAO profile = new ProfileDAO();
        ArrayList<Watched> watchedList = profile.getWatched(profileId);

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Watched watched : watchedList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblTitle = new Label(watched.getProgramma());
            lblTitle.getStyleClass().add("accountEmail");
            lblTitle.setLayoutX(65);
            lblTitle.setLayoutY(14);

            Label lblGenre = new Label(watched.getGenre());
            lblGenre.getStyleClass().add("accountLabels");
            lblGenre.setLayoutY(37);
            lblGenre.setLayoutX(65);

            Label lblLan = new Label(watched.getLanguage());
            lblLan.getStyleClass().add("accountLabels");
            lblLan.setLayoutY(54);
            lblLan.setLayoutX(65);

            Label lblAge = new Label(Integer.toString(watched.getAge()));
            lblAge.getStyleClass().add("accountLabels");
            lblAge.setLayoutY(73);
            lblAge.setLayoutX(65);

            Label lblPlaytimeInfo = new Label("Playtime in minutes: ");
            lblPlaytimeInfo.getStyleClass().add("accountLabels");
            lblPlaytimeInfo.setLayoutY(54);
            lblPlaytimeInfo.setLayoutX(253);

            Label lblPlaytime = new Label(Integer.toString(watched.getPlaytime()));
            lblPlaytime.getStyleClass().add("accountLabels");
            lblPlaytime.setLayoutY(54);
            lblPlaytime.setLayoutX(390);

            if (watched.getEpisode() != null) {
                Label lblSerieTitle = new Label(watched.getEpisode());
                lblSerieTitle.getStyleClass().add("accountLabels");
                lblSerieTitle.setLayoutY(14);
                lblSerieTitle.setLayoutX(253);

                Label lblVolume = new Label(watched.getFollowNumber());
                lblVolume.getStyleClass().add("accountLabels");
                lblVolume.setLayoutY(73);
                lblVolume.setLayoutX(253);
                accountPane.getChildren().addAll(lblSerieTitle, lblVolume);
            }

            Button btnEdit = new Button("Edit");
            btnEdit.setLayoutX(1089);
            btnEdit.setLayoutY(55);
            btnEdit.setMinWidth(80);
            btnEdit.getStyleClass().add("accountButtons");
            btnEdit.setOnAction(e -> stage.setScene(new WatchedGUI().editWatched(stage, watched, email)));

            Button btnDelete = new Button("Delete");
            btnDelete.setLayoutX(1089);
            btnDelete.setLayoutY(13);
            btnDelete.setMinWidth(80);
            btnDelete.getStyleClass().add("accountButtons");
            btnDelete.setId("btnDeleteWatched");
            ProfileController controller = new ProfileController(stage);
            controller.setWatch(watched);
            controller.setEmail(email);
            btnDelete.setOnAction(controller);

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            accountPane.getChildren().addAll(lblTitle, lblAge, lblGenre, lblPlaytimeInfo, lblLan, lblPlaytime);
            accountPane.getChildren().addAll(btnEdit, btnDelete);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }
        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage),scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    public Scene editWatched(Stage stage, Watched watched,String email) {
        AnchorPane mainPane = new AnchorPane();
        mainPane.prefHeight(800.0);
        mainPane.setMinWidth(900.0);
        mainPane.setStyle("-fx-background-color: #545454;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(407);
        scrollPane.setLayoutY(50);
        scrollPane.setMinWidth(1140);
        scrollPane.setMinHeight(700);
        scrollPane.setMaxHeight(700);

        VBox verticalBox = new VBox();
        verticalBox.setMinWidth(1160);
        verticalBox.setMinHeight(Region.USE_COMPUTED_SIZE);

        Label lblPercentage = new Label("Watched percentage");
        lblPercentage.setLayoutX(465);
        lblPercentage.setLayoutY(53);
        lblPercentage.getStyleClass().add("whiteLabels");


        TextField txtPercentage = new TextField(Integer.toString((int)watched.getWatchedPercentage()));
        txtPercentage.setLayoutX(666);
        txtPercentage.setLayoutY(53);

        ProfileController controller = new ProfileController(stage);
        controller.setTxtPercentage(txtPercentage);
        controller.setWatch(watched);
        controller.setEmail(email);


        Button btnUpdate = new Button("Submit changes");
        btnUpdate.setId("btnUpdate");
        btnUpdate.setLayoutX(465);
        btnUpdate.setLayoutY(138);
        btnUpdate.getStyleClass().add("accountButtons");
        btnUpdate.setOnAction(controller);

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPercentage, txtPercentage, btnUpdate);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}
