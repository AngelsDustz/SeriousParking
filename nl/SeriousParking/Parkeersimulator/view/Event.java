package nl.SeriousParking.Parkeersimulator.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class Event extends View<SimulatorController, Simulator>{


    private TableView<Reserv> table = new TableView<Reserv>();
    private final ObservableList<Reserv> data =
            FXCollections.observableArrayList(
                    new Reserv("12:30", "Smith", "13:30"),
                    new Reserv("12:30", "Johnson", "13:30"));
    final HBox hb = new HBox();


    public Event(SimulatorController controller, Simulator model)  {
        super(controller, model);

        final Label label = new Label("Reservaties");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn StartTimeCol = new TableColumn("Start Time");
        StartTimeCol.setMinWidth(100);
        StartTimeCol.setCellValueFactory(
                new PropertyValueFactory<Reserv, String>("StartTime"));

        TableColumn NameCol = new TableColumn("Name");
        NameCol.setMinWidth(100);
        NameCol.setCellValueFactory(
                new PropertyValueFactory<Reserv, String>("Name"));

        TableColumn EndTimeCol = new TableColumn("EndTime");
        EndTimeCol.setMinWidth(200);
        EndTimeCol.setCellValueFactory(
                new PropertyValueFactory<Reserv, String>("EndTime"));

        table.setItems(data);
        table.getColumns().addAll(StartTimeCol, NameCol, EndTimeCol);

        final TextField addStartTime = new TextField();
        addStartTime.setPromptText("Start Time");
        addStartTime.setMaxWidth(StartTimeCol.getPrefWidth());
        final TextField addName = new TextField();
        addName.setMaxWidth(NameCol.getPrefWidth());
        addName.setPromptText("Name");
        final TextField addEndTime = new TextField();
        addEndTime.setMaxWidth(EndTimeCol.getPrefWidth());
        addEndTime.setPromptText("EndTime");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Reserv(
                        addStartTime.getText(),
                        addName.getText(),
                        addEndTime.getText()));
                addStartTime.clear();
                addName.clear();
                addEndTime.clear();
            }
        });

        hb.getChildren().addAll(addStartTime, addName, addEndTime, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        this.getChildren().add(vbox);
    }

    @Override
    public void update(){

    }


    public static class Reserv {

        private final SimpleStringProperty StartTime;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty EndTime;

        private Reserv(String sTime, String lName, String EndTime) {
            this.StartTime = new SimpleStringProperty(sTime);
            this.Name = new SimpleStringProperty(lName);
            this.EndTime = new SimpleStringProperty(EndTime);
        }

        public String getStartTime() {
            return StartTime.get();
        }

        public void setStartTime(String sTime) {
            StartTime.set(sTime);
        }

        public String getName() {
            return Name.get();
        }

        public void setName(String sTime) {
            Name.set(sTime);
        }

        public String getEndTime() {
            return EndTime.get();
        }

        public void setEndTime(String sTime) {
            EndTime.set(sTime);
        }
    }
}
