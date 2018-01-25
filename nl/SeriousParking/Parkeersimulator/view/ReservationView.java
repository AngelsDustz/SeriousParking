package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nl.SeriousParking.Parkeersimulator.controller.ReservationController;
import nl.SeriousParking.Parkeersimulator.model.Reservation;
import nl.SeriousParking.Parkeersimulator.model.ReservationContainer;


public class ReservationView extends View<ReservationController, ReservationContainer> {


    private TextField[] input;

    public TableView<Reservation> table = new TableView<Reservation>();


    final HBox hb = new HBox();


    public ReservationView(ReservationController controller, ReservationContainer model) {
        super(controller, model);
        input = new TextField[4];

        Label label = new Label("Reservationatie's");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn StartTimeCol = new TableColumn("Start Time");
        StartTimeCol.setMinWidth(100);
        StartTimeCol.setCellValueFactory(
                new PropertyValueFactory<Reservation, String>("StartTime"));

        TableColumn NameCol = new TableColumn("Name");
        NameCol.setMinWidth(100);
        NameCol.setCellValueFactory(
                new PropertyValueFactory<Reservation, String>("Name"));

        TableColumn EndTimeCol = new TableColumn("EndTime");
        EndTimeCol.setMinWidth(200);
        EndTimeCol.setCellValueFactory(
                new PropertyValueFactory<Reservation, String>("EndTime"));

        table.setItems(model.data);
        table.getColumns().addAll(StartTimeCol, NameCol, EndTimeCol);

        input[0] = new TextField();
        input[0].setPromptText("Start Time");
        input[0].setMaxWidth(StartTimeCol.getPrefWidth());

        input[1] = new TextField();
        input[1].setMaxWidth(NameCol.getPrefWidth());
        input[1].setPromptText("Name");

        input[2] = new TextField();
        input[2].setMaxWidth(EndTimeCol.getPrefWidth());
        input[2].setPromptText("EndTime");

        Button addButton = new Button("Add");

        addButton.setOnAction(e -> {
            String[] content = new String[3];
            int i=0;
            for(TextField textField: input){
                if(textField!=null) {
                    content[i] = textField.getText();
                    i++;
                }
                else{
                    break;
                }
            }
            controller.add(content);
        });


        hb.getChildren().addAll(input[0], input[1], input[2], addButton);
        hb.setSpacing(3);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        this.getChildren().add(vbox);
    }

    @Override
    public void update() {

    }


}
