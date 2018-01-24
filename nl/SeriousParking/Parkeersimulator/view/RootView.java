package nl.SeriousParking.Parkeersimulator.view;


import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.ProfitController;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Profit;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class RootView {



    public void RootView(Stage primaryStage) {
        primaryStage.setTitle("SeriousParking Parkeersimulator");

        SplitPane splitPanebottom = new SplitPane();
        SplitPane splitPanetop = new SplitPane();

        Scene scene = new Scene(splitPanebottom,1500,900);

        splitPanebottom.setOrientation(Orientation.VERTICAL);
        splitPanebottom.prefWidthProperty().bind(scene.widthProperty());
        splitPanebottom.prefHeightProperty().bind(scene.heightProperty());

        splitPanetop.setOrientation(Orientation.HORIZONTAL);
        splitPanetop.prefWidthProperty().bind(scene.widthProperty());
        splitPanetop.prefHeightProperty().bind(scene.heightProperty());

        Simulator model                 = new Simulator();
        SimulatorController controller  = new SimulatorController(model);
        SimView view                    = new SimView(controller, model);

        SettingHandler handler          = new SettingHandler();
        SettingsController settingsc    = new SettingsController(handler);
        SimSettings simsettings         = new SimSettings(settingsc, handler);

        TextView TextView               = new TextView(controller, model);

        Profit profit                   = new Profit();
        ProfitController profitC        = new ProfitController(profit);
        ProfitView profitView           = new ProfitView(profitC, profit);

        model.addEventListner(profit);

        ScrollPane scrollPane   = new ScrollPane(simsettings);
        ScrollPane scrollPane2  = new ScrollPane(view);
        PieChartView piechart   = new PieChartView(controller, model);
        PieChartView2 piechart2 = new PieChartView2(controller, model);
        SimData legend          = new SimData(controller, model);
        TextView textview       = new TextView(controller, model);
        TabPane tabPane         = new TabPane();
        TabPane pie             = new TabPane();
        BorderPane borderPane   = new BorderPane();
        Button start            = new Button("Start/Stop");
        Button reset            = new Button("Reset");
        Button tick             = new Button("single tick");
        ToolBar toolBar         = new ToolBar();

        scrollPane.setFitToHeight(true);
        scrollPane2.setFitToHeight(true);

        start.setOnAction(e -> {
            controller.startSimulator();
        });

        reset.setOnAction(e -> {
            controller.resetSimulator();
        });

        tick.setOnAction(e -> {
            controller.tick();
        });


        toolBar.getItems().addAll(start,reset,tick);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        pie.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        Tab TextViewTab = new Tab();
        TextViewTab.setText("Text View");
        TextViewTab.setContent(textview);
        tabPane.getTabs().add(TextViewTab);

        Tab SimSettings = new Tab();
        SimSettings.setText("Settings");
        SimSettings.setContent(scrollPane);
        tabPane.getTabs().add(SimSettings);

        Tab ProfitTab   = new Tab();
        ProfitTab.setText("Inkomsten");
        ProfitTab.setContent(profitView);
        tabPane.getTabs().add(ProfitTab);

        Tab PieChart1 = new Tab();
        PieChart1.setText("Bezetting");
        PieChart1.setContent(piechart);
        pie.getTabs().add(PieChart1);

        Tab PieChart2 = new Tab();
        PieChart2.setText("AdHoc/pass");
        PieChart2.setContent(piechart2);
        pie.getTabs().add(PieChart2);

        legend.setAlignment(Pos.CENTER);

        borderPane.setCenter(scrollPane2);
        //borderPane.setRight(legend);
        borderPane.setBottom(toolBar);

        splitPanetop.getItems().addAll(borderPane,pie);

        splitPanebottom.getItems().addAll(splitPanetop,tabPane);

        primaryStage.setScene(scene);
        scene.getStylesheets().add("Chart.css");

        primaryStage.show();
    }
}
