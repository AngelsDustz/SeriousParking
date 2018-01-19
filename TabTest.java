import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class TabTest extends Application {
    @Override


    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulator");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);
        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();
        // generate 3 tabs
        for (int i = 0; i < 3; i++) {
            Tab tab = new Tab();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
            tab.setText("View" + i);
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label("View" + i));
            hbox.setAlignment(Pos.CENTER);
            tab.setContent(hbox);
            tabPane.getTabs().add(tab);
        }

        // add tab pane
        borderPane.setCenter(tabPane);
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        // add border Pane
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}