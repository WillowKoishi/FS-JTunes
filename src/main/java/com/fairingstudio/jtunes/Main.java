package com.fairingstudio.jtunes;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import io.datafx.controller.flow.FlowException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.stage.StageStyle;

public class Main extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @Override
    public void start(Stage stage) throws IOException, FlowException {


        Flow flow = new Flow(MainController.class);

        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);



        JFXDecorator decorator = new JFXDecorator(stage,container.getView() ,false,false,true);
        decorator.setCustomMaximize(true);


        decorator.setGraphic(new SVGGlyph(""));
        stage.setMinWidth(600);
        stage.setMinHeight(400);


        stage.setTitle("FS JTunes");

        double width = 600;
        double height = 400;
        try {
            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
            width = bounds.getWidth() / 2;
            height = bounds.getHeight() / 2;
        }catch (Exception e){ }

        Scene scene = new Scene(decorator, width, height);
        scene.setFill(Color.TRANSPARENT);

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                Main.class.getClassLoader().getResource("com/fairingstudio/jtunes/css/main-style.css").toExternalForm());



        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("assets/img/logo.png"));

        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }



}