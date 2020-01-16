package GUI;

import Domain.Account;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {
    public VBox getMenu(Stage stage) {
        VBox menu = new VBox();
        menu.setLayoutX(3.0);
        menu.setLayoutY(6.0);
        menu.setMinHeight(800.0);
        menu.setMinWidth(395.0);
        menu.setStyle("-fx-background-color: #383838;");
        AnchorPane.setBottomAnchor(menu,0.0);
        AnchorPane.setLeftAnchor(menu,0.0);
        AnchorPane.setTopAnchor(menu,0.0);

        Image imageLogo = new Image("file:NetflixLogo.png");
        ImageView logo = new ImageView();
        logo.setFitHeight(151.0);
        logo.setFitWidth(375.0);
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);
        logo.setImage(imageLogo);

        Button accountBtn = new Button("Account");
        accountBtn.setId("accountBtn");
        accountBtn.getStyleClass().add("menuBtn");
        accountBtn.setOnAction(e -> stage.setScene(new Accounts().AccountList(stage)));

        Button movieBtn = new Button("Movie");
        movieBtn.setMnemonicParsing(false);
        accountBtn.setId("movieBtn");
        movieBtn.getStyleClass().add("menuBtn");
        movieBtn.setOnAction(e -> stage.setScene(new Movies().movieList(stage)));

        Button serieBtn = new Button("Serie");
        serieBtn.setMnemonicParsing(false);
        accountBtn.setId("serieBtn");
        serieBtn.getStyleClass().add("menuBtn");
        serieBtn.setOnAction(e -> stage.setScene(new Series().serieList(stage)));

        //Add margin to the nodes in the VBox
        VBox.setMargin(logo, new Insets(15.0, 0.0, 0.0, 37.0));
        VBox.setMargin(accountBtn, new Insets(10.0, 10.0, 10.0, 10.0));
        VBox.setMargin(movieBtn, new Insets(10.0, 10.0, 10.0, 10.0));
        VBox.setMargin(serieBtn, new Insets(10.0, 10.0, 10.0, 10.0));

        //Can't use addAll to add nodes to menu, needs to be fixed
        menu.getChildren().add(logo);
        menu.getChildren().add(accountBtn);
        menu.getChildren().add(movieBtn);
        menu.getChildren().add(serieBtn);

        return menu;
    }
}
