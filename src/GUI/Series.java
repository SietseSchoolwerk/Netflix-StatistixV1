package GUI;

import Database.AccountDAO;
import Database.EpisodeDAO;
import Database.SerieDAO;
import Domain.Program;
import Domain.Serie;
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

public class Series {
    /**
     * List of all the series
     * @param stage
     * @return scene
     */
    public Scene serieList(Stage stage) {
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

        SerieDAO serieDAO = new SerieDAO();
        ArrayList<Serie> serieList = serieDAO.getAllSeries();

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Serie serie : serieList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblTitle = new Label(serie.getTitle());
            lblTitle.getStyleClass().add("accountEmail");
            lblTitle.setLayoutX(65);
            lblTitle.setLayoutY(14);

            Label lblRecommend = new Label(serie.getRecommend());
            lblRecommend.getStyleClass().add("accountLabels");
            lblRecommend.setLayoutY(37);
            lblRecommend.setLayoutX(65);

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            Button btnAvgWatchedOfSerie = new Button("Episodes watched AVG of all profiles");
            btnAvgWatchedOfSerie.setLayoutX(910);
            btnAvgWatchedOfSerie.setLayoutY(13);
            btnAvgWatchedOfSerie.setMinWidth(142);
            btnAvgWatchedOfSerie.getStyleClass().add("accountButtons");
            btnAvgWatchedOfSerie.setOnAction(e -> stage.setScene(new Series().avgWatchedEpisodes(stage, serie.getTitle())));

            accountPane.getChildren().addAll(lblTitle, lblRecommend, btnAvgWatchedOfSerie);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);

        }

        Label lblPageTitle = new Label("Serie overview");
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle, scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    /**
     * Scene
     * @param stage
     * @param email
     * @return
     */
    public Scene serieListWithAccount(Stage stage, String email) {
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

        SerieDAO serieDAO = new SerieDAO();
        ArrayList<Serie> serieList = serieDAO.getAllSeries();

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Serie serie : serieList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblTitle = new Label(serie.getTitle());
            lblTitle.getStyleClass().add("accountEmail");
            lblTitle.setLayoutX(65);
            lblTitle.setLayoutY(14);

            Label lblRecommend = new Label(serie.getRecommend());
            lblRecommend.getStyleClass().add("accountLabels");
            lblRecommend.setLayoutY(37);
            lblRecommend.setLayoutX(65);

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            Button btnAvgWatchedOfSerie = new Button("AVG watched episodes of account");
            btnAvgWatchedOfSerie.setLayoutX(910);
            btnAvgWatchedOfSerie.setLayoutY(13);
            btnAvgWatchedOfSerie.setMinWidth(142);
            btnAvgWatchedOfSerie.getStyleClass().add("accountButtons");
            btnAvgWatchedOfSerie.setOnAction(e -> stage.setScene(new Series().avgWatchedEpisodesWithAccount(stage, serie.getTitle(), email)));

            accountPane.getChildren().addAll(lblTitle, lblRecommend, btnAvgWatchedOfSerie);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().addAll(accountPane);

        }

        Label lblPageTitle = new Label("Serie overview with account " + email);
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage),lblPageTitle, scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    /**
     * A list with the average watched per episode
     * @param stage
     * @param serie
     * @return Scene
     */
    public Scene avgWatchedEpisodes(Stage stage, String serie){
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

        EpisodeDAO episodeDAO = new EpisodeDAO();
        SerieDAO serieDAO = new SerieDAO();
        ArrayList<Program> serieList = episodeDAO.getAllEpisodes(serie);

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Program program : serieList) {
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

            Label lblProfilesFullyWatched = new Label(serieDAO.getAvgWatchedPercentageFromSeriePerEpisode(program.getProgramId()) +" % average watched of total time");
            lblProfilesFullyWatched.getStyleClass().add("accountLabels");
            lblProfilesFullyWatched.setLayoutY(54);
            lblProfilesFullyWatched.setLayoutX(910);

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            accountPane.getChildren().addAll(lblTitle, lblGenre, lblLan, lblAge, lblPlaytimeInfo, lblPlaytime, lblProfilesFullyWatched);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);

        }

        Label lblPageTitle = new Label("Average watched percentage of an episode with all profiles");
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage),lblPageTitle, scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    public Scene avgWatchedEpisodesWithAccount(Stage stage, String serie, String email){
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

        EpisodeDAO episodeDAO = new EpisodeDAO();
        SerieDAO serieDAO = new SerieDAO();
        ArrayList<Program> serieList = episodeDAO.getAllEpisodes(serie);

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Program program : serieList) {
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

            Label lblProfilesFullyWatched = new Label(serieDAO.getAvgWatchedPercentageFromSeriePerEpisodeWithAccount(program.getProgramId(), email) +" % average watched of total time");
            lblProfilesFullyWatched.getStyleClass().add("accountLabels");
            lblProfilesFullyWatched.setLayoutY(54);
            lblProfilesFullyWatched.setLayoutX(910);

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            accountPane.getChildren().addAll(lblTitle, lblGenre, lblLan, lblAge, lblPlaytimeInfo, lblPlaytime, lblProfilesFullyWatched);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);

        }

        Label lblPageTitle = new Label("Average watched percentage of an episode from one account");
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle, scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}
