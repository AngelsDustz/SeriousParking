package nl.SeriousParking.Parkeersimulator.view;


import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.controller.RuntimeController;
import nl.SeriousParking.Parkeersimulator.model.Runtime;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;



public class RootView {
    public void RootView(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(RootView.class.getResourceAsStream("ico.png")));;
        primaryStage.setTitle("SeriousParking Parkeersimulator");
        //Window indeling.
        SplitPane splitPanebottom   = new SplitPane();
        SplitPane splitPanetop      = new SplitPane();
        Scene scene                 = new Scene(splitPanebottom,1500,900);

        splitPanebottom.setOrientation(Orientation.VERTICAL);
        splitPanetop.setOrientation(Orientation.HORIZONTAL);

        //Model, View, Controller intitializing.

        //Simulator.
        Simulator model                     = new Simulator();
        SimulatorController controller      = new SimulatorController(model);

        //GarageView
        GarageView view                     = new GarageView(controller, model);

        //Settings
        SettingHandler handler              = new SettingHandler();
        SettingsController settingsc        = new SettingsController(handler);
        SimSettings simsettings             = new SimSettings(settingsc, handler);

        //Profit
        ProfitView profitView               = new ProfitView(controller, model);

        //Runtime
        Runtime runtime                     = new Runtime();
        RuntimeController runtimeController = new RuntimeController(runtime);
        RuntimeView runtimeView             = new RuntimeView(runtimeController, runtime);

        //View panes?
        PieChartView piechart   = new PieChartView(controller, model);
        PieChartView2 piechart2 = new PieChartView2(controller, model);
        TextView textview       = new TextView(controller, model);
        BorderPane borderPane   = new BorderPane();
        TabPane tabPane         = new TabPane();
        TabPane pie             = new TabPane();
        ScrollPane scrollPane   = new ScrollPane(simsettings);
        Button start            = new Button("Start/Stop");
        Button reset            = new Button("Reset");
        Button tick             = new Button("single tick");
        Button tick100          = new Button("tick +100");
        start.setDefaultButton(true);
        reset.setCancelButton(true);

        ToolBar toolBar         = new ToolBar();

        scrollPane.setFitToHeight(true);

        //Button action handles.
        start.setOnAction(e -> {

            controller.startSimulator();
        });

        reset.setOnAction(e -> {
            controller.resetSimulator();
        });

        tick.setOnAction(e -> {
            controller.tick();
        });


        tick100.setOnAction(e -> {
            controller.tickMany(100);
        });


        toolBar.getItems().addAll(start,reset,tick,tick100, runtimeView);

        //Tabs
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        pie.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //Text view
        Tab TextViewTab = new Tab();
        TextViewTab.setText("Text View");
        TextViewTab.setContent(textview);
        tabPane.getTabs().add(TextViewTab);

        //Events
        Tab Events = new Tab();
        Events.setText("Events");
        //Events.setContent();
        tabPane.getTabs().add(Events);

        //Inkomsten
        Tab ProfitTab   = new Tab();
        ProfitTab.setText("Inkomsten");
        ProfitTab.setContent(profitView);
        tabPane.getTabs().add(ProfitTab);

        //Settings
        Tab SimSettings = new Tab();
        SimSettings.setText("Settings");
        SimSettings.setContent(scrollPane);
        tabPane.getTabs().add(SimSettings);

        //Bezetting
        Tab PieChart1 = new Tab();
        PieChart1.setText("Bezetting");
        PieChart1.setContent(piechart);
        pie.getTabs().add(PieChart1);

        //Adhoc/pass
        Tab PieChart2 = new Tab();
        PieChart2.setText("AdHoc/pass");
        PieChart2.setContent(piechart2);
        pie.getTabs().add(PieChart2);

        //Add to scene
        borderPane.setCenter(view);
        borderPane.setBottom(toolBar);

        pie.maxWidthProperty().bind(splitPanetop.widthProperty().multiply(0.3));
        tabPane.maxHeightProperty().bind(splitPanebottom.heightProperty().multiply(0.35));

        splitPanetop.getItems().addAll(borderPane,pie);
        splitPanebottom.getItems().addAll(splitPanetop,tabPane);

        primaryStage.setScene(scene);
        scene.getStylesheets().add("Style.css");
        primaryStage.show();
    }
}
