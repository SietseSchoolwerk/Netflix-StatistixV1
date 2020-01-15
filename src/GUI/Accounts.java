package GUI;

import Domain.Account;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Accounts {
    public Scene AccountList(Stage stage){
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

        ArrayList<Account> accountList = getAccounts();

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Account accounts: accountList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblEmail = new Label(accounts.getEmail());
            lblEmail.getStyleClass().add("accountEmail");
            lblEmail.setLayoutX(65);
            lblEmail.setLayoutY(14);

            Label lblSubscriber = new Label(accounts.getSubscriber());
            lblSubscriber.getStyleClass().add("accountLabels");
            lblSubscriber.setLayoutY(37);
            lblSubscriber.setLayoutX(65);

            Label lblAdress = new Label(accounts.getAddress());
            lblAdress.getStyleClass().add("accountLabels");
            lblAdress.setLayoutX(65);
            lblAdress.setLayoutY(54);

            Label lblCity = new Label(accounts.getCity());
            lblCity.getStyleClass().add("accountLabels");
            lblCity.setLayoutY(71);
            lblCity.setLayoutX(65);

            Button btnEdit = new Button("Edit");
            btnEdit.setLayoutX(1089);
            btnEdit.setLayoutY(55);
            btnEdit.setMinWidth(80);
            btnEdit.getStyleClass().add("accountButtons");

            Button btnDelete = new Button("Delete");
            btnDelete.setLayoutX(1089);
            btnDelete.setLayoutY(13);
            btnDelete.setMinWidth(80);
            btnDelete.getStyleClass().add("accountButtons");

            Button btnProfiles = new Button("Profiles");
            btnProfiles.setLayoutX(910);
            btnProfiles.setLayoutY(13);
            btnProfiles.setMinWidth(142);
            btnProfiles.getStyleClass().add("accountButtons");
            btnProfiles.setOnAction(e -> stage.setScene(new Profiles().profileList(stage, accounts.getEmail())));

            Button btnWatchedSeries = new Button("Watched series");
            btnWatchedSeries.setLayoutX(910);
            btnWatchedSeries.setLayoutY(55);
            btnWatchedSeries.setMinWidth(142);
            btnWatchedSeries.getStyleClass().add("accountButtons");

            Button btnWatchedMovies = new Button("Watched movies");
            btnWatchedMovies.setLayoutX(732);
            btnWatchedMovies.setLayoutY(13);
            btnWatchedMovies.getStyleClass().add("accountButtons");

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255,255,255));

            accountPane.getChildren().addAll(lblEmail, lblSubscriber, lblAdress, lblCity);
            accountPane.getChildren().addAll(btnEdit, btnDelete, btnProfiles, btnWatchedSeries, btnWatchedMovies);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        Button btnAddNewAccount = new Button("New Account");
        btnAddNewAccount.getStyleClass().add("accountButtons");
        btnAddNewAccount.getStyleClass().add("newButton");
        btnAddNewAccount.setLayoutY(2);
        btnAddNewAccount.setLayoutX(407);

        mainPane.getChildren().addAll(menu.getMenu(stage),scrollPane, btnAddNewAccount);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();

        accountList.add(new Account("Daan@icloud.com", "da", "daan hayman", "Minckelersweg 43", "Hoogerheide"));
        accountList.add(new Account("Daan@icloud.com", "da", "daan hayman", "Minckelersweg 43", "Hoogerheide"));
        accountList.add(new Account("Daan@icloud.com", "da", "daan hayman", "Minckelersweg 43", "Hoogerheide"));

        return accountList;
    }
}
