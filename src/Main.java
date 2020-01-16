import GUI.Menu;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane mainPane = new AnchorPane();
        mainPane.setStyle("-fx-background-color: #545454;");

        Menu menu = new Menu();
        mainPane.getChildren().addAll(menu.getMenu(stage));

        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/netflix.css").toExternalForm());
        stage.setResizable(false);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setTitle("Daan Hayman 2153233, Sietse van Tilburg 2155963, Michel Jacobusse 2164786");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Taskbar.png")));
        stage.show();
    }
}
