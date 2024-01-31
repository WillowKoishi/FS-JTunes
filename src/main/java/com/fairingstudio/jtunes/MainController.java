package com.fairingstudio.jtunes;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.JFXHamburger;
import io.datafx.controller.FXMLController;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.SimpleFormatter;

@FXMLController(value = "Main.fxml", title = "Material Design Example")
public class MainController {

    @FXML
    private Label label1;

    @FXML
    private JFXHamburger menu_ham;

    public void changelabel(String text){
        label1.setText(text);
    }

    @PostConstruct
    public void init(){
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (SerialPort port:serialPorts) {
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
                Platform.runLater(()->{
                    label1.setText(String.valueOf(new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis())));
                });


            }
        };
        timer.schedule(task,500,500);
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