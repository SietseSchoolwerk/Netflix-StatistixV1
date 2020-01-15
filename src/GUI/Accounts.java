package GUI;

import Controllers.AccountController;
import Database.AccountDAO;
import Database.DatabaseConnection;
import Domain.Account;
import javafx.geometry.Insets;
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

        AccountDAO accountDAO = new AccountDAO();
        ArrayList<Account> accountList = accountDAO.getAllAccounts();

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
            btnEdit.setOnAction(e -> stage.setScene(new Accounts().editAccount(stage, accounts)));

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

    public Scene editAccount(Stage stage, Account account) {
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

        Label lblPassword = new Label("Password account");
        lblPassword.setLayoutX(465);
        lblPassword.setLayoutY(53);
        lblPassword.getStyleClass().add("whiteLabels");

        Label lblSubscriber = new Label("Subscriber");
        lblSubscriber.setLayoutX(465);
        lblSubscriber.setLayoutY(93);
        lblSubscriber.getStyleClass().add("whiteLabels");

        Label lblAddress = new Label("Address");
        lblAddress.setLayoutX(465);
        lblAddress.setLayoutY(133);
        lblAddress.getStyleClass().add("whiteLabels");

        Label lblCity = new Label("City");
        lblCity.setLayoutX(465);
        lblCity.setLayoutY(173);
        lblCity.getStyleClass().add("whiteLabels");

        TextField txtPassword = new TextField(account.getPassword());
        txtPassword.setLayoutX(666);
        txtPassword.setLayoutY(53);

        TextField txtSubscriber = new TextField(account.getSubscriber());
        txtSubscriber.setLayoutX(666);
        txtSubscriber.setLayoutY(93);

        TextField txtAddress = new TextField(account.getAddress());
        txtAddress.setLayoutX(666);
        txtAddress.setLayoutY(133);

        TextField txtCity = new TextField(account.getCity());
        txtCity.setLayoutX(666);
        txtCity.setLayoutY(173);

        AccountController controller = new AccountController(stage, account);
        controller.setTxtPasswordAccount(txtPassword);
        controller.setTxtSubscriberAccount(txtSubscriber);
        controller.setTxtAddressAccount(txtAddress);
        controller.setTxtCityAccount(txtCity);

        Button btnSubmit = new Button("Submit changes");
        btnSubmit.setId("btnSubmit");
        btnSubmit.setLayoutX(465);
        btnSubmit.setLayoutY(218);
        btnSubmit.getStyleClass().add("accountButtons");
        btnSubmit.setOnAction(controller);

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPassword, lblSubscriber, lblAddress, lblCity, txtPassword, txtSubscriber, txtAddress, txtCity, btnSubmit);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}
