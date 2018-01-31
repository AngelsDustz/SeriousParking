/*
package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.util.Pair;


public class TestDialog {

    public void TestDialog() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Garage variabelen");
        dialog.setHeaderText("Set Garage variabelen");

        // Set the button types.
        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        // Create the labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField adhocfloor = new TextField();
        adhocfloor.setPromptText("adhoc verdiepingen");
        TextField passreserfloor = new TextField();
        passreserfloor.setPromptText("passhouders verdiepingen");
        TextField places = new TextField();
        places.setPromptText("places");
        TextField rows = new TextField();
        rows.setPromptText("rows");

        grid.add(new Label("adhoc verdiepingen:"), 0, 0);
        grid.add(adhocfloor, 1, 0);
        grid.add(new Label("passhouders verdiepingen:"), 0, 1);
        grid.add(passreserfloor, 1, 1);
        grid.add(new Label("places:"), 0, 2);
        grid.add(places, 1, 2);
        grid.add(new Label("rows:"), 0, 3);
        grid.add(rows, 1, 3);

        dialog.initStyle(StageStyle.UTILITY);
        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();

    }

}
*/