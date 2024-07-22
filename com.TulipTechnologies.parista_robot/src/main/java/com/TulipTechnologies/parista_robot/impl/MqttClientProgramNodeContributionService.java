package com.TulipTechnologies.parista_robot.impl;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.domain.URCapAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

class MqttClientProgramNodeContributionService implements ProgramNodeContribution

{
    
    private final URCapAPI api;
    private final DataModel model;
    private final String URI = "tcp://192.168.1.21:1883";
    // private final ScriptWriter writer;
    private MqttAsyncClient client;
    private static final Logger logger = Logger.getLogger(MqttClientProgramNodeContributionService.class.getName());


    public MqttClientProgramNodeContributionService(URCapAPI api, DataModel model)
        {
            this.api = api;
            this.model = model;
            // this.writer = null;
            // MqqtConnect();
            MqttConnect();

        }

    // private void MqtConnect()
    // {
    //     try
    //     {
    //         this.client = new MqttClient(URI,MqttClient.generateClientId());
    //         this.client.connect();
    //         model.set("connectionStatus", "Connection Suceeded!");
    //         // writer.appendLine("textmsg(\"Connection Succeed !\")");
    //     }
    //     catch( MqttException e )
    //     {
    //         e.printStackTrace();
    //         // writer.appendLine("textmsgs(\"Connection Failed !\")");
    //         model.set("conectionStatus", "Connection Failed!" + e.getMessage());
    //     }

    // }

    // private void MqttConnect() {
    //     try {
    //         client = new MqttAsyncClient(URI, MqttAsyncClient.generateClientId());
    //         client.connect(null, new IMqttActionListener() {
    //             @Override
    //             public void onSuccess(IMqttToken asyncActionToken) {
    //                 model.set("connectionStatus", "Connection Succeeded!");
    //             }

    //             @Override
    //             public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
    //                 model.set("connectionStatus", "Connection Failed: " + exception.getMessage());
    //             }
    //         });
    //     } catch (MqttException e) {
    //         model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
    //     }
    // }

    

    public void closeConnection()
    {
        if(this.client != null && this.client.isConnected())
        {
            try
            {
                this.client.disconnect();
                this.client.close();
            } catch(MqttException e)
            {
                e.printStackTrace();
                model.set("connectionStatus", "Disconnection Failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void openView() {
    }

    @Override
    public void closeView() {
        closeConnection();
    }

    @Override
    public String getTitle() {
        return "MQTT Client Service !";
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public void generateScript(ScriptWriter writer) {
      String status = model.get("connectionStatus", "No connection attempt made");
      writer.appendLine("textmsg(\"" + status + "\")");
    }
}
