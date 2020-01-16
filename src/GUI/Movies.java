package GUI;

import Database.AccountDAO;
import Database.FilmDAO;
import Database.ProgramDAO;
import Domain.Film;
import Domain.Program;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Movies {
    public Scene movieList(Stage stage) {
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

        FilmDAO filmDAO = new FilmDAO();
        ArrayList<Program> movieList = filmDAO.getAllMovies();

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Program program : movieList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblTitle = new Label(program.getTitle());
            lblTitle.getStyleClass().add("accountEmail");
            lblTitle.setLayoutX(65);
            lblTitle.setLayoutY(14);

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

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            accountPane.getChildren().addAll(lblTitle, lblGenre, lblLan, lblAge, lblPlaytimeInfo, lblPlaytime);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);

        }

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage), scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}
