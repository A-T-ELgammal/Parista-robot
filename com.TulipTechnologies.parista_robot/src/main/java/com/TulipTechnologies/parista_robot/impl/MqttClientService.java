// package com.TulipTechnologies.parista_robot.impl;

// import org.eclipse.paho.client.mqttv3.IMqttClient;
// import org.eclipse.paho.client.mqttv3.MqttClient;
// import org.eclipse.paho.client.mqttv3.MqttException;
// import org.eclipse.paho.client.mqttv3.MqttMessage;
// import org.osgi.framework.BundleContext;

// public class MqttClientService {
//     private static final String MQTT_BROKER = "ssl://192.168.1.21:1883"; // Change to your broker URL
//     private static final String CLIENT_ID = "URCapClient";
//     private static final String TOPIC = "order/drink";

//     private IMqttClient mqttClient;
//     private final BundleContext context;

//     public MqttClientService(BundleContext context) {
//         this.context = context;
//         try {
//             mqttClient = new MqttClient(MQTT_BROKER, CLIENT_ID);
//             mqttClient.connect();
//             mqttClient.subscribe(TOPIC, this::messageArrived);
//         } catch (MqttException e) {
//             e.printStackTrace();
//         }
//     }

//     public void messageArrived(String topic, MqttMessage message) throws Exception {
//         String payload = new String(message.getPayload());
//         System.out.println("Message received: " + payload);

//         // Add your code to handle the message and execute URScript commands
//         if ("1".equals(payload)) {
//             sendScriptCommand("movej(p[0.5, -0.5, 0.2, 0, 3.14, 0], a=1.2, v=0.25)");
//         } else if ("2".equals(payload)) {
//             sendScriptCommand("movej(p[-0.5, 0.5, 0.2, 0, 3.14, 0], a=1.2, v=0.25)");
//         }
//     }

//     private void sendScriptCommand(String script) {
//         try {
//             ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "echo '" + script + "' > /programs/script.script");
//             processBuilder.start();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public void disconnect() {
//         try {
//             if (mqttClient != null && mqttClient.isConnected()) {
//                 mqttClient.disconnect();
//             }
//         } catch (MqttException e) {
//             e.printStackTrace();
//         }
//     }
// }

// package com.TulipTechnologies.parista_robot.impl;

// import java.io.FileWriter;
// import java.io.IOException;

// import org.eclipse.paho.client.mqttv3.IMqttClient;
// import org.eclipse.paho.client.mqttv3.MqttClient;
// import org.eclipse.paho.client.mqttv3.MqttException;
// import org.eclipse.paho.client.mqttv3.MqttMessage;

// public class MqttClientService {
//     private static final String MQTT_BROKER = "tcp://192.168.1.21:1883"; // Change to your broker URL
//     private static final String CLIENT_ID = "URCapClient";
//     private static final String TOPIC = "order/drink";

//     private IMqttClient mqttClient;
    

//     public MqttClientService() {
        
//         try {
//             mqttClient = new MqttClient(MQTT_BROKER, CLIENT_ID);
//             mqttClient.connect();
//             mqttClient.subscribe(TOPIC, this::messageArrived);
//         } catch (MqttException e) {
//             e.printStackTrace();
//         }
//     }

//     public void messageArrived(String topic, MqttMessage message) {
//         String payload = new String(message.getPayload());
//         System.out.println("Message received: " + payload);

//         // Add your code to handle the message and execute URScript commands
//         if ("1".equals(payload)) {
//             sendScriptCommand("movej(p[0.5, -0.5, 0.2, 0, 3.14, 0], a=1.2, v=0.25)");

//         } else if ("2".equals(payload)) {
//             sendScriptCommand("movej(p[-0.5, 0.5, 0.2, 0, 3.14, 0], a=1.2, v=0.25)");
//         }
//     }

//     private void sendScriptCommand(String script) {
//         try (FileWriter writer = new FileWriter("/programs/script.script")) {
//             writer.write(script);
//             writer.flush();
//             // Execute the script by sending the file path to the controller
//             ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "echo 'load /programs/script.script' | netcat -q 0 127.0.0.1 30002");
//             processBuilder.start();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public void disconnect() {
//         try {
//             if (mqttClient != null && mqttClient.isConnected()) {
//                 mqttClient.disconnect();
//             }
//         } catch (MqttException e) {
//             e.printStackTrace();
//         }
//     }
// }


