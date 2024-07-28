// // package com.TulipTechnologies.parista_robot.impl;

// // import org.eclipse.paho.client.mqttv3.MqttClient;
// // import org.eclipse.paho.client.mqttv3.MqttException;

// // import com.ur.urcap.api.contribution.ProgramNodeContribution;
// // import com.ur.urcap.api.domain.URCapAPI;
// // import com.ur.urcap.api.domain.data.DataModel;
// // import com.ur.urcap.api.domain.script.ScriptWriter;

// // public class MqttClientProgramNodeContributionService implements ProgramNodeContribution {

// //     private final URCapAPI api;
// //     private final DataModel model;
// //     private final String URI = "tcp://192.168.1.21:1883";
// //     private MqttClient client;
// //     private ScriptWriter writer;
// //     public MqttClientProgramNodeContributionService(URCapAPI api, DataModel model) {
// //         this.api = api;
// //         this.model = model;
// //         MqttConnect();
// //     }

// //     // private void initiateMqttConnection() {
// //     //     new Thread(new Runnable() {
// //     //         @Override
// //     //         public void run() {
// //     //             MqttConnect();
// //     //         }
// //     //     }).start();
// //     // }

// //     private void MqttConnect() {
// //         try {
// //             client = new MqttClient(URI, MqttClient.generateClientId());
// //             client.connect();
// //             model.set("connectionStatus", "Connection Succeeded!");
// //             String status = model.get("connectionStatus", "No connection attempt made");
// //             writer.appendLine("textmsg(\"" + status + "\")");
// //             writer.appendLine("textmsg(\"" + status + "\")");
// //             }

// //         catch (MqttException e) {
// //             model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
// //         }

// //     }

// //     public void closeConnection() {
// //         if (this.client != null && this.client.isConnected()) {
// //             try {
// //                 this.client.disconnect();
// //                 this.client.close();
// //             } catch (MqttException e) {
// //                 e.printStackTrace();
// //                 model.set("connectionStatus", "Disconnection Failed: " + e.getMessage());
// //                 String status = model.get("connectionStatus", "No connection attempt made");
// //                 writer.appendLine("textmsg(\"" + status + "\")");
// //             }
// //         }
// //     }

// //     @Override
// //     public void openView() {
// //     }

// //     @Override
// //     public void closeView() {
// //     }

// //     @Override
// //     public String getTitle() {
// //         return "MQTT Client Service!";
// //     }

// //     @Override
// //     public boolean isDefined() {
// //         return true;
// //     }

// //     @Override
// //     public void generateScript(ScriptWriter writer) {
// //         this.writer = writer;
// //         String status = model.get("connectionStatus", "No connection attempt made");
// //         writer.appendLine("textmsg(\"" + status + "\")");
// //         // writer.appendLine("movej([0.1, -0.5, 1.2, -1.0, 0.5, 0.3], a=1.2, v=0.25)\n");
// //     }
// // }

// ///////////////////////////////////////////////////////////////////////////////////
// package com.TulipTechnologies.parista_robot.impl;

// import org.eclipse.paho.client.mqttv3.MqttClient;
// import org.eclipse.paho.client.mqttv3.MqttException;

// import com.ur.urcap.api.contribution.ProgramNodeContribution;
// import com.ur.urcap.api.domain.URCapAPI;
// import com.ur.urcap.api.domain.data.DataModel;
// import com.ur.urcap.api.domain.script.ScriptWriter;

// public class MqttClientProgramNodeContributionService implements ProgramNodeContribution {

//     private final URCapAPI api;
//     private final DataModel model;
//     private final String URI = "tcp://192.168.1.21:1883";
//     private MqttClient client;
//     private ScriptWriter writer;
//     private boolean connectionStatusChanged;

//     public MqttClientProgramNodeContributionService(URCapAPI api, DataModel model) {
//         this.api = api;
//         this.model = model;
//     }

//     private void MqttConnect() {
//         try {
//             this.client = new MqttClient(URI, MqttClient.generateClientId());
//             this.client.connect();
//             model.set("connectionStatus", "Connection Succeeded!");
//             connectionStatusChanged = true; // Set flag to true
//         } catch (MqttException e) {
//             model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
//             connectionStatusChanged = true; // Set flag to true
//         }
//     }

//     public void closeConnection() {
//         if (this.client != null && this.client.isConnected()) {
//             try {
//                 this.client.disconnect();
//                 this.client.close();
//                 model.set("connectionStatus", "Disconnected Successfully");
//                 connectionStatusChanged = true; // Set flag to true
//             } catch (MqttException e) {
//                 e.printStackTrace();
//                 model.set("connectionStatus", "Disconnection Failed: " + e.getMessage());
//                 connectionStatusChanged = true; // Set flag to true
//             }
//         }
//     }


//     @Override
//     public void openView() {
//     }

//     @Override
//     public void closeView() {
//         closeConnection();
//     }

//     @Override
//     public String getTitle() {
//         return "MQTT Client Service!";
//     }

//     @Override
//     public boolean isDefined() {
//         return true;
//     }

//     @Override
//     public void generateScript(ScriptWriter writer) {
//         this.writer = writer;
//         // String status = model.get("connectionStatus", "No connection attempt made");
//         // writer.appendLine("textmsg(\"" + status + "\")");

//         if (connectionStatusChanged) { // Only update message if connection status has changed
//             String status = model.get("connectionStatus", "No connection attempt made");
//             writer.appendLine("textmsg(\"" + status + "\")");
//             connectionStatusChanged = false; // Reset flag
//     }
//     }
// }

// //////////////////////////////////////////////////////////////////////////////////













// // package com.TulipTechnologies.parista_robot.impl;

// // import java.util.logging.Logger;

// // import org.eclipse.paho.client.mqttv3.IMqttActionListener;
// // import org.eclipse.paho.client.mqttv3.IMqttToken;
// // import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
// // import org.eclipse.paho.client.mqttv3.MqttException;

// // import com.ur.urcap.api.contribution.ProgramNodeContribution;
// // import com.ur.urcap.api.domain.URCapAPI;
// // import com.ur.urcap.api.domain.data.DataModel;
// // import com.ur.urcap.api.domain.script.ScriptWriter;

// // class MqttClientProgramNodeContributionService implements ProgramNodeContribution

// // {
    
// //     private final URCapAPI api;
// //     private final DataModel model;
// //     private final String URI = "tcp://192.168.1.21:1883";
// //     // private final ScriptWriter writer;
// //     private MqttAsyncClient client;
// //     private static final Logger logger = Logger.getLogger(MqttClientProgramNodeContributionService.class.getName());


// //     public MqttClientProgramNodeContributionService(URCapAPI api, DataModel model)
// //         {
// //             this.api = api;
// //             this.model = model;
// //             // this.writer = null;
// //             // MqqtConnect();
// //             MqttConnect();

// //         }

// //     // private void MqtConnect()
// //     // {
// //     //     try
// //     //     {
// //     //         this.client = new MqttClient(URI,MqttClient.generateClientId());
// //     //         this.client.connect();
// //     //         model.set("connectionStatus", "Connection Suceeded!");
// //     //         // writer.appendLine("textmsg(\"Connection Succeed !\")");
// //     //     }
// //     //     catch( MqttException e )
// //     //     {
// //     //         e.printStackTrace();
// //     //         // writer.appendLine("textmsgs(\"Connection Failed !\")");
// //     //         model.set("conectionStatus", "Connection Failed!" + e.getMessage());
// //     //     }

// //     // }

// //     // private void MqttConnect() {
// //     //     try {
// //     //         client = new MqttAsyncClient(URI, MqttAsyncClient.generateClientId());
// //     //         client.connect(null, new IMqttActionListener() {
// //     //             @Override
// //     //             public void onSuccess(IMqttToken asyncActionToken) {
// //     //                 model.set("connectionStatus", "Connection Succeeded!");
// //     //             }

// //     //             @Override
// //     //             public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
// //     //                 model.set("connectionStatus", "Connection Failed: " + exception.getMessage());
// //     //             }
// //     //         });
// //     //     } catch (MqttException e) {
// //     //         model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
// //     //     }
// //     // }

// //     private void MqttConnect() {
// //         try {
// //             client = new MqttAsyncClient(URI, MqttAsyncClient.generateClientId());
// //             client.connect(null, new IMqttActionListener() {
// //                 @Override
// //                 public void onSuccess(IMqttToken asyncActionToken) {
// //                     model.set("connectionStatus", "Connection Succeeded!");
// //                 }

// //                 @Override
// //                 public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
// //                     model.set("connectionStatus", "Connection Failed: " + exception.getMessage());
// //                 }
// //             });
// //         } catch (MqttException e) {
// //             model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
// //         }
// //     }


// //     public void closeConnection()
// //     {
// //         if(this.client != null && this.client.isConnected())
// //         {
// //             try
// //             {
// //                 this.client.disconnect();
// //                 this.client.close();
// //             } catch(MqttException e)
// //             {
// //                 e.printStackTrace();
// //                 model.set("connectionStatus", "Disconnection Failed: " + e.getMessage());
// //             }
// //         }
// //     }

// //     @Override
// //     public void openView() {
// //     }

// //     @Override
// //     public void closeView() {
// //         closeConnection();
// //     }

// //     @Override
// //     public String getTitle() {
// //         return "MQTT Client Service !";
// //     }

// //     @Override
// //     public boolean isDefined() {
// //         return true;
// //     }

// //     @Override
// //     public void generateScript(ScriptWriter writer) {
// //       String status = model.get("connectionStatus", "No connection attempt made");
// //       writer.appendLine("textmsg(\"" + status + "\")");
// //     }
// // }
package com.TulipTechnologies.parista_robot.impl;

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

    public MqttClientProgramNodeContributionService(URCapAPI api, DataModel model) {
        this.api = api;
        this.model = model;
    }

    private void MqttConnect() {
        try {
            client = new MqttAsyncClient(URI, MqttAsyncClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(0);            

            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    model.set("connectionStatus", "Connection Succeeded!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    model.set("connectionStatus", "Connection Failed: " + exception.getMessage());
                }
            });
        } catch (MqttException e) {
            model.set("connectionStatus", "Connection Setup Failed: " + e.getMessage());
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
        MqttConnect();
        String status = model.get("connectionStatus", "No connection attempt made");
        writer.appendLine("textmsg(\"" + status + "\")");
        // writer.appendLine("movej([0.1, -0.5, 1.2, -1.0, 0.5, 0.3], a=1.2, v=0.25)\n");
        
    }
}
