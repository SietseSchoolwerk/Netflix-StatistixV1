package GUI;

import Controllers.AccountController;
import Database.AccountDAO;
import Database.DatabaseConnection;
import Domain.Account;
import Domain.Film;
import Domain.Profile;
import Domain.Program;
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
    public Scene AccountList(Stage stage) {
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

        AccountDAO accountDAO = new AccountDAO();
        ArrayList<Account> accountList = accountDAO.getAllAccounts();

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        /** To load all the accounts in a scroll list within the application */
        for (Account accounts : accountList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            /** Changing the color of the row every time */
            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblEmail = new Label("Email: " +accounts.getEmail());
            lblEmail.getStyleClass().add("headLabels");
            lblEmail.setLayoutX(65);
            lblEmail.setLayoutY(14);

            Label lblSubscriber = new Label("Subscriber: " +accounts.getSubscriber());
            lblSubscriber.getStyleClass().add("labels");
            lblSubscriber.setLayoutY(37);
            lblSubscriber.setLayoutX(65);

            Label lblAdress = new Label("Address: " +accounts.getAddress());
            lblAdress.getStyleClass().add("labels");
            lblAdress.setLayoutX(65);
            lblAdress.setLayoutY(54);

            Label lblCity = new Label("City: " +accounts.getCity());
            lblCity.getStyleClass().add("labels");
            lblCity.setLayoutY(71);
            lblCity.setLayoutX(65);

            Button btnEdit = new Button("Edit");
            btnEdit.setLayoutX(1089);
            btnEdit.setLayoutY(55);
            btnEdit.setMinWidth(80);
            btnEdit.getStyleClass().add("buttons");
            btnEdit.setOnAction(e -> stage.setScene(new Accounts().editAccount(stage, accounts)));

            Button btnDelete = new Button("Delete");
            btnDelete.setLayoutX(1089);
            btnDelete.setLayoutY(13);
            btnDelete.setMinWidth(80);
            btnDelete.getStyleClass().add("buttons");
            btnDelete.setId("btnDelete");
            AccountController controller = new AccountController(stage, accounts);
            btnDelete.setOnAction(controller);

            Button btnProfiles = new Button("Profiles");
            btnProfiles.setLayoutX(910);
            btnProfiles.setLayoutY(13);
            btnProfiles.setMinWidth(142);
            btnProfiles.getStyleClass().add("buttons");
            btnProfiles.setOnAction(e -> stage.setScene(new Profiles().profileList(stage, accounts.getEmail())));

            Button btnSeries = new Button("Series");
            btnSeries.setLayoutX(732);
            btnSeries.setLayoutY(13);
            btnSeries.setMinWidth(142);
            btnSeries.setMinHeight(70);
            btnSeries.getStyleClass().add("buttons");
            btnSeries.setOnAction(e -> stage.setScene(new Series().serieListWithAccount(stage, accounts.getEmail())));

            Button btnWatchedMovies = new Button("Watched movies");
            btnWatchedMovies.setLayoutX(910);
            btnWatchedMovies.setLayoutY(55);
            btnWatchedMovies.setMinWidth(142);
            btnWatchedMovies.getStyleClass().add("buttons");
            btnWatchedMovies.setOnAction(e -> stage.setScene(watchedMovies(stage, accounts.getEmail())));

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            accountPane.getChildren().addAll(lblEmail, lblSubscriber, lblAdress, lblCity);
            accountPane.getChildren().addAll(btnEdit, btnDelete, btnProfiles, btnSeries, btnWatchedMovies);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        Label lblPageTitle = new Label("Account overview");
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        /** A button sending us to the create new account page. */
        Button btnAddNewAccount = new Button("New Account");
        btnAddNewAccount.getStyleClass().add("buttons");
        btnAddNewAccount.getStyleClass().add("newButton");
        btnAddNewAccount.setLayoutY(2);
        btnAddNewAccount.setLayoutX(1444);
        btnAddNewAccount.setOnAction(e -> stage.setScene(addAccount(stage)));

        /** Creating a button to send us to the page with all the accounts with one profile */
        Button btnAccountsWithOneProfile = new Button("Accounts with 1 profile");
        btnAccountsWithOneProfile.getStyleClass().add("buttons");
        btnAccountsWithOneProfile.getStyleClass().add("newButton");
        btnAccountsWithOneProfile.setLayoutY(2);
        btnAccountsWithOneProfile.setLayoutX(1140);
        btnAccountsWithOneProfile.setOnAction(e -> stage.setScene(accountsWithOneProfile(stage)));

        mainPane.getChildren().addAll(menu.getMenu(stage), scrollPane, btnAddNewAccount, btnAccountsWithOneProfile, lblPageTitle);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }


    /**
     * This function sends a scene
     * This scence is the editing of the account
     * @param stage
     * @param account
     * @return scene of the edit account
     *
     * This is a smaller scene and not 1600 pixels wide, since this is not needed in this case
     *
     */
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


        //Creating a new controller
        //Assigning all data to the controller.
        AccountController controller = new AccountController(stage, account);
        controller.setTxtPasswordAccount(txtPassword);
        controller.setTxtSubscriberAccount(txtSubscriber);
        controller.setTxtAddressAccount(txtAddress);
        controller.setTxtCityAccount(txtCity);

        Button btnSubmit = new Button("Submit changes");
        btnSubmit.setId("btnSubmit");
        btnSubmit.setLayoutX(666);
        btnSubmit.setMinWidth(149);
        btnSubmit.setLayoutY(258);
        btnSubmit.getStyleClass().add("buttons");

        //Attaching the controller to the onAction.
        btnSubmit.setOnAction(controller);

        Label lblPageTitle = new Label("Edit account: ");
        lblPageTitle.setLayoutX(465);
        lblPageTitle.getStyleClass().add("lblEditTitle");

        Label lblAccount = new Label(account.getEmail());
        lblAccount.setLayoutX(620);
        lblAccount.getStyleClass().add("lblEditTitle");

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage),lblAccount , lblPageTitle, lblPassword, lblSubscriber, lblAddress, lblCity, txtPassword, txtSubscriber, txtAddress, txtCity, btnSubmit);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    /**
     * This returns the scene of add account
     * This is again a smaller view so not 1600 pixels wide
     * @param stage
     * @return a scene of add account.
     */
    public Scene addAccount(Stage stage) {
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

        Label lblEmail = new Label("Email");
        lblEmail.setLayoutX(465);
        lblEmail.setLayoutY(53);
        lblEmail.getStyleClass().add("whiteLabels");

        Label lblPassword = new Label("Password");
        lblPassword.setLayoutX(465);
        lblPassword.setLayoutY(93);
        lblPassword.getStyleClass().add("whiteLabels");

        Label lblSubscriber = new Label("Subscriber");
        lblSubscriber.setLayoutX(465);
        lblSubscriber.setLayoutY(133);
        lblSubscriber.getStyleClass().add("whiteLabels");

        Label lblAddress = new Label("Address");
        lblAddress.setLayoutX(465);
        lblAddress.setLayoutY(173);
        lblAddress.getStyleClass().add("whiteLabels");

        Label lblCity = new Label("City");
        lblCity.setLayoutX(465);
        lblCity.setLayoutY(213);
        lblCity.getStyleClass().add("whiteLabels");

        TextField txtEmail = new TextField();
        txtEmail.setLayoutX(666);
        txtEmail.setLayoutY(53);

        TextField txtPassword = new TextField();
        txtPassword.setLayoutX(666);
        txtPassword.setLayoutY(93);

        TextField txtSubscriber = new TextField();
        txtSubscriber.setLayoutX(666);
        txtSubscriber.setLayoutY(133);

        TextField txtAddress = new TextField();
        txtAddress.setLayoutX(666);
        txtAddress.setLayoutY(173);

        TextField txtCity = new TextField();
        txtCity.setLayoutX(666);
        txtCity.setLayoutY(213);

        //Creates a new instance of the accountcontroller
        //Attaching of all the variables to the controller.
        AccountController controller = new AccountController(stage);
        controller.setTxtEmailAccount(txtEmail);
        controller.setTxtPasswordAccount(txtPassword);
        controller.setTxtPasswordAccount(txtPassword);
        controller.setTxtSubscriberAccount(txtSubscriber);
        controller.setTxtAddressAccount(txtAddress);
        controller.setTxtCityAccount(txtCity);

        Button btnSubmit = new Button("Add Account");
        btnSubmit.setId("btnAddAccount");
        btnSubmit.setLayoutX(666);
        btnSubmit.setMinWidth(149);
        btnSubmit.setLayoutY(258);
        btnSubmit.getStyleClass().add("buttons");
        //Binding the controller to the onaction of the add button.
        btnSubmit.setOnAction(controller);

        Label lblPageTitle = new Label("Add account");
        lblPageTitle.setLayoutX(465);
        lblPageTitle.getStyleClass().add("lblEditTitle");

        Menu menu = new Menu();

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle, lblEmail, lblPassword, lblSubscriber, lblAddress, lblCity, txtPassword, txtSubscriber, txtAddress, txtCity, txtEmail, btnSubmit);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }

    /**
     * Displaying all the accounts with only one profile
     * @param stage
     * @return a scene with all the accounts with only one profile.
     */
    public Scene accountsWithOneProfile(Stage stage) {

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

        AccountDAO accountDAO = new AccountDAO();
        ArrayList<Account> accountList = accountDAO.getAllAccountsWithOneProfile();

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Account accounts : accountList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblEmail = new Label("Email: " +accounts.getEmail());
            lblEmail.getStyleClass().add("headLabels");
            lblEmail.setLayoutX(65);
            lblEmail.setLayoutY(14);

            Label lblSubscriber = new Label("Subscriber: " + accounts.getSubscriber());
            lblSubscriber.getStyleClass().add("labels");
            lblSubscriber.setLayoutY(37);
            lblSubscriber.setLayoutX(65);

            Label lblAdress = new Label("Address: " +accounts.getAddress());
            lblAdress.getStyleClass().add("labels");
            lblAdress.setLayoutX(65);
            lblAdress.setLayoutY(54);

            Label lblCity = new Label("City: " +accounts.getCity());
            lblCity.getStyleClass().add("labels");
            lblCity.setLayoutY(71);
            lblCity.setLayoutX(65);

            Line line = new Line();
            line.setStartX(-100);
            line.setEndX(1081);
            line.setLayoutX(101);
            line.setLayoutY(109);
            line.setStroke(javafx.scene.paint.Color.rgb(255, 255, 255));

            accountPane.getChildren().addAll(lblEmail, lblSubscriber, lblAdress, lblCity);
            accountPane.getChildren().add(line);

            verticalBox.getChildren().add(accountPane);
        }

        Label lblPageTitle = new Label("Accounts with only one profile");
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
     * Returns the scene of a list of all the watched movies within an account
     * @param stage
     * @param email
     * @return a scene with a list of all watched movies.
     */
    public Scene watchedMovies(Stage stage, String email) {
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

        AccountDAO accountDAO = new AccountDAO();
        ArrayList<Program> movieList = accountDAO.getAllWatchedMovies(email);

        scrollPane.setStyle("-fx-background: #383838;");

        int count = 0;
        for (Program movie : movieList) {
            count++;
            AnchorPane accountPane = new AnchorPane();
            accountPane.setMinHeight(110);
            accountPane.setMinWidth(1160);

            if ((count % 2) == 0) {
                accountPane.setStyle("-fx-background-color: #4d4d4d;");
            } else {
                accountPane.setStyle("-fx-background-color: #383838;");
            }

            Label lblTitle = new Label(movie.getTitle());
            lblTitle.getStyleClass().add("headLabels");
            lblTitle.setLayoutX(65);
            lblTitle.setLayoutY(14);

            Label lblGenre = new Label("Genre: " +(movie.getGenre()));
            lblGenre.getStyleClass().add("labels");
            lblGenre.setLayoutY(37);
            lblGenre.setLayoutX(65);

            Label lblLan = new Label("Language: " +movie.getLanguage());
            lblLan.getStyleClass().add("labels");
            lblLan.setLayoutY(54);
            lblLan.setLayoutX(65);

            Label lblAge = new Label("Age:" +(movie.getAge()));
            lblAge.getStyleClass().add("labels");
            lblAge.setLayoutY(73);
            lblAge.setLayoutX(65);

            Label lblPlaytimeInfo = new Label("Playtime in minutes: ");
            lblPlaytimeInfo.getStyleClass().add("labels");
            lblPlaytimeInfo.setLayoutY(54);
            lblPlaytimeInfo.setLayoutX(253);

            Label lblPlaytime = new Label(movie.getLengthOfTime());
            lblPlaytime.getStyleClass().add("labels");
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

        Label lblPageTitle = new Label("Watched movies of " + email);
        lblPageTitle.setLayoutX(407);
        lblPageTitle.getStyleClass().add("lblPageTitle");

        Menu menu = new Menu();
        scrollPane.setContent(verticalBox);

        mainPane.getChildren().addAll(menu.getMenu(stage), lblPageTitle , scrollPane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());

        return scene;
    }
}

