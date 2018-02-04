package nl.SeriousParking.Parkeersimulator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import nl.SeriousParking.Parkeersimulator.controller.EventController;
import nl.SeriousParking.Parkeersimulator.model.SimEvent;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

import java.util.Optional;

import static javafx.scene.paint.Color.RED;

public class EventView extends View<EventController, Simulator> {
    private ListView<String> listView       = new ListView<>();

    private TextField fldname       = new TextField();
    private TextField fldday        = new TextField();
    private TextField fldweek       = new TextField();
    private TextField fldAdhoc      =new TextField();
    private TextField fldRes        =new TextField();
    private TextField fldPass       =new TextField();

    public EventView(EventController controller, Simulator model) {
        super(controller, model);
        update();
        FlowPane container      = new FlowPane();
        Button   addButton      = new Button("add new event");
        addButton.setOnAction(new EventHandler<ActionEvent>(){


            @Override
            public void handle(ActionEvent t){

                Dialog<ButtonType> adddialog = new Dialog<>();
                adddialog.setTitle("Add an event");
                adddialog.setHeaderText("Add an event");

                ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.APPLY);
                adddialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                Label WarningDay = new Label("in numbers!");
                Label WarningWeek= new Label("in numbers!");


                WarningDay.setTextFill(RED);
                WarningWeek.setTextFill(RED);


                grid.add(new Label("Name:"), 0, 0);
                grid.add(fldname, 1, 0);
                grid.add(new Label("Week:"), 0, 1);
                grid.add(fldweek, 1, 1);
                grid.add(WarningDay,2,1);
                grid.add(new Label("Day:"), 0, 2);
                grid.add(fldday, 1, 2);
                grid.add(WarningWeek,2,2);

                grid.add(new Label("Number adhoc cars per hour"), 0, 3);
                grid.add(fldAdhoc, 1, 3);

                grid.add(new Label("Number passholder cars per hour"), 0, 4);
                grid.add(fldPass, 1, 4);

                grid.add(new Label("Number reservation cars per hour"), 0, 5);
                grid.add(fldRes, 1, 5);



                adddialog.getDialogPane().setContent(grid);
                Optional<ButtonType> result = adddialog.showAndWait();
                if (result.get() == addButtonType) {

                    SimEvent add = new SimEvent();
                    add.setTitle(fldname.getText());
                    try{
                        controller.addEvent(add
                        ,(fldname.getText()),(Integer.parseInt(fldweek.getText()))
                        ,(Integer.parseInt(fldday.getText()))
                        ,(Integer.parseInt(fldAdhoc.getText()))
                        ,(Integer.parseInt(fldPass.getText()))
                        ,(Integer.parseInt(fldRes.getText())));
                    }catch (NumberFormatException e) {
                            Dialog dialog2 = new Dialog();
                            dialog2.setHeaderText("ERROR");
                            dialog2.setContentText("something went Wrong \n" + e + " Please use the correct input");
                            dialog2.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                            Node closeButton = dialog2.getDialogPane().lookupButton(ButtonType.CLOSE);
                            closeButton.managedProperty().bind(closeButton.visibleProperty());
                            closeButton.setVisible(true);
                            dialog2.showAndWait();
                    }
                }
            }
        });

        container.setHgap(10);
        container.setVgap(10);
        
        container.getChildren().addAll(listView,addButton);
        this.getChildren().add(container);
        model.addView(this);
    }

    @Override
    public void update() {
        listView.getItems().clear();

        for (SimEvent e : model.getEvents()) {
            if (e.isActive()) {
                controller.doEvent(e);
                listView.getItems().add(e.getTitle()+" IS ACTIVE!");

            } else {
                listView.getItems().add(e.getTitle()+" not active.");
            }
        }
    }


}
