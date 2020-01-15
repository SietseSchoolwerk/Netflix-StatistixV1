import Domain.Account;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        Account acc = new Account("88email@emailc.com",
                "88password",
                "88subscriber",
                "88address",
                "88city"
        );



//        AnchorPane mainPane = new AnchorPane();
//        mainPane.setStyle("-fx-background-color: #545454;");
//
//        Menu menu = new Menu();
//        mainPane.getChildren().addAll(menu.getMenu(stage));
//
//        Scene scene = new Scene(mainPane);
//        stage.setScene(scene);
//        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());
//        stage.setResizable(false);
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
//        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
//        stage.show();
    }
}
