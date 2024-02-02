package com.fairingstudio.jtunes;


import com.fairingstudio.jtunes.datafx.ExtendedAnimatedFlowContainer;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.*;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;

@FXMLController(value = "Main.fxml", title = "FS JTunes")
public class MainController {

    public JFXToolbar burgerTooltip;
    @FXML
    private BorderPane main_pane;

    @FXMLViewFlowContext
    private ViewFlowContext context;



    @FXML
    private JFXButton clear_log;
    @FXML
    private JFXListView<String> serial_logs_listview;
    private ObservableList<String> dataList = FXCollections.observableArrayList();


    @FXML
    private SerialComController serialComController;

    @FXML
    private JFXHamburger menu_ham;



    @PostConstruct
    public void init() throws FlowException, IOException {
        for(int i=0;i<5;i++){
            Label label = new Label(String.valueOf(Math.random()*10e50)+"1231231"+String.valueOf(Math.random()*10e50)+"afadfasd"+String.valueOf(Math.random()*10e50));
            dataList.add(String.valueOf(String.valueOf(Math.random()*10e50)+"1231231"+String.valueOf(Math.random()*10e50)+"afadfasd"+String.valueOf(Math.random()*10e50)));
        }
        serial_logs_listview.setItems(dataList);
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (SerialPort port : serialPorts) {
            System.out.println("串口名称:" + port.getSystemPortName());
            System.out.println("串口类型:" + port.getPortDescription());
            System.out.println("串口完整类型:" + port.getDescriptivePortName());
        }
        serialPorts[0].addDataListener(mainDataListener);

        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //System.out.println( System.currentTimeMillis());
                Platform.runLater(() -> {
                    dataList.add("log堆栈测试，目前已堆了："+dataList.size()+"层||||"+String.valueOf(String.valueOf(Math.random()*10e50)+"1231231"+String.valueOf(Math.random()*10e50)+"afadfasd"+String.valueOf(Math.random()*10e50)));
                    serial_logs_listview.scrollTo(dataList.size());
                });
            }
        };
        timer.schedule(task, 500, 20);
        clear_log.setOnAction((event)->{
            dataList.clear();
        });

    }

    SerialPortDataListener mainDataListener = new SerialPortDataListener() {
        @Override
        public int getListeningEvents() {
            return 0;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {

        }
    };

}