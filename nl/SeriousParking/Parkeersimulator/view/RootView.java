package nl.SeriousParking.Parkeersimulator.view;


import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
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
        SettingsView simsettings             = new SettingsView(settingsc, handler);

        //Profit
        ProfitView profitView               = new ProfitView(controller, model);


        //View panes?
        PieChartView piechart   = new PieChartView(controller, model);
        PieChartView2 piechart2 = new PieChartView2(controller, model);
        TextView textview       = new TextView(controller, model);
        TabPane tabPane         = new TabPane();
        TabPane pie             = new TabPane();


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
        SimSettings.setContent(simsettings);
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

        pie.maxWidthProperty().bind(splitPanetop.widthProperty().multiply(0.3));
        tabPane.maxHeightProperty().bind(splitPanebottom.heightProperty().multiply(0.35));

        splitPanetop.getItems().addAll(view,pie);
        splitPanebottom.getItems().addAll(splitPanetop,tabPane);

        primaryStage.setScene(scene);
        scene.getStylesheets().add("Style.css");
        primaryStage.show();
    }
}
