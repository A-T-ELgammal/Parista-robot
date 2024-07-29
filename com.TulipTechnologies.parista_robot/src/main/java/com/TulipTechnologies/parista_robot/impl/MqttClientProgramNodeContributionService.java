package com.TulipTechnologies.parista_robot.impl;


import java.util.concurrent.CountDownLatch;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.domain.URCapAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class MqttClientProgramNodeContributionService implements ProgramNodeContribution {

    private final URCapAPI api;
    private final DataModel model;
    private final String URI = "tcp://192.168.1.21:1883";
    private MqttAsyncClient client;
    private CountDownLatch connectionLatch;
    private boolean isConnected = false;
    private String payload;
    private String warning = "No warning yet!";
    public MqttClientProgramNodeContributionService(URCapAPI api, DataModel model) {
        this.api = api;
        this.model = model;
    }
    private void initMqttClient() 
    {
        try 
        {
            client = new MqttAsyncClient(URI, MqttAsyncClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            
            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    isConnected = true;
                    warning += "MQTT Connection Succeeded!\n";
                    subscribeToTopic("order");
                }
                
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    isConnected = false;
                    warning +="MQTT Connection Failed: " + exception.getMessage();
                }
            });
        } catch (MqttException e) {
            warning += "MQTT Setup Failed: " + e.getMessage() + "\n";
        }
    }
    
    public void closeConnection() {
        if (client != null && client.isConnected()) {
            try {
                client.disconnect(null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        model.set("connectionStatus", "Disconnected Successfully");
                    }
                    
                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        model.set("connectionStatus", "Disconnection Failed: " + exception.getMessage());
                    }
                });
                client.close();
            } catch (MqttException e) {
                model.set("connectionStatus", "Disconnection Failed: " + e.getMessage());
            }
        }
    }
    
    private void subscribeToTopic(String topic)
    {
        while(client.isConnected())
        {
            try{
            client.subscribe(topic, 0,(topic1, message) -> 
            {
            this.payload = message.getPayload().toString();                
            });
            
            }catch(MqttException e)
            {
                String subscribtionStatus = "subscribe Failed";
            }
        }
    }
    
    String excuteCommand(String payload)
    {
        if (payload == "1")
        {
            return "movej([0.5, -1.2, 1.8, -2.0, -1.5, 0.3] ,a = 1.2, v = 1.05)" + "sleep(1.0)";
        }
            return "movej([-0.3, -1.1, 1.5, -1.7, 1.0, 0.8] ,a = 1.2, v = 1.05)" + "sleep(1.0)";
    }
    

    @Override
    public void openView() {
        // Optionally handle view opening logic
    }

    @Override
    public void closeView() {
        
    }
    
    @Override
    public String getTitle() {
        return "MQTT Client Service!";
    }
    
    @Override
    public boolean isDefined() {
        return true;
    }
    
    @Override
    public void generateScript(ScriptWriter writer) {

        // MqttConnect();
        initMqttClient();
        String status = isConnected? "MQTT Connected" : "MQTT Not Connected";
        // String status = model.get("connectionStatus", "No connection attempt made");
        writer.appendLine("textmsg(\"" + status + "\")");
        writer.appendLine(excuteCommand(this.payload));
        // writer.appendLine("movej([0.1, -0.5, 1.2, -1.0, 0.5, 0.3], a=1.2, v=0.25)\n");
        
    }
}


    // private void MqttConnect(){
    //     try {

    //         MqttConnectOptions options = new MqttConnectOptions();
    //         options.setAutomaticReconnect(true);
    //         options.setCleanSession(true);
    //         options.setConnectionTimeout(0);

    //         connectionLatch = new CountDownLatch(1);

    //         client.connect(options, null, new IMqttActionListener() {
    //             @Override
    //             public void onSuccess(IMqttToken asyncActionToken) {
    //                 model.set("connectionStatus", "Connection Succeeded!");
    //                 connectionLatch.countDown();
    //             }

    //             @Override
    //             public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
    //                 model.set("connectionStatus", "Connection Failed: " + exception.getMessage());
    //                 connectionLatch.countDown();
    //             }
    //         });
    //         if (!connectionLatch.await(15, TimeUnit.SECONDS))
    //         {
    //             model.set("connectionStatus", "Connection Timed Out");
    //         }
    //     } catch (MqttException | InterruptedException e) 
    //     {
        //         model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
        //     }
        // }
        
        