package com.TulipTechnologies.parista_robot.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class SocketInstallationNodeContribution implements InstallationNodeContribution {
    private static final String IP_KEY = "ip";
    private static final String PORT_KEY = "port";
    private final DataModel model;
    private JTextField ipField;
    private JTextField portField;
    private String IP;
    private String Port;
    public SocketInstallationNodeContribution(DataModel model) {
        this.model = model;
    }

    @Override
    public void openView() {
        // This method is called when the view is opened

    }

    @Override
    public void closeView() {
        // This method is called when the view is closed
    }

    
    public JPanel getConfigurationComposite(JPanel jPanel) {
        buildUI(jPanel);
        return jPanel;
    }

    public void buildUI(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Company Logo at the title position
        JLabel logoLabel = new JLabel();
        try {
            logoLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/logo.png"))));
            logoLabel.setSize(30, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.add(logoLabel);
        panel.add(logoPanel);

        JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ipPanel.add(new JLabel("Device IP:"));
        ipField = new JTextField(15);
        ipField.setPreferredSize(new Dimension(200, 25));
        ipField.setText(getIP());
        ipField.setForeground(java.awt.Color.GRAY);
        ipField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ipField.getText().equals("Enter the device IP...")) {
                    ipField.setText("");
                    ipField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ipField.getText().isEmpty()) {
                    ipField.setForeground(java.awt.Color.GRAY);
                    ipField.setText("Enter the device IP...");
                }
            }
        });
        ipPanel.add(ipField);
        panel.add(ipPanel);

        JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        portPanel.add(new JLabel("Port:"));
        portField = new JTextField(15);
        // portField.setPreferredSize(new Dimension(200, 25));
        portField.setText(getPort());
        portField.setForeground(java.awt.Color.GRAY);
        portField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (portField.getText().equals("Enter port number...")) {
                    portField.setText("");
                    portField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (portField.getText().isEmpty()) {
                    portField.setForeground(java.awt.Color.GRAY);
                    portField.setText("Enter port number...");
                }
            }
        });
        portPanel.add(portField);
        panel.add(portPanel);

        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(e -> 
        {
         updateConnection();  
          String messageToShow = "device Connected with ip: " + getIP() + " & port: " + getPort();
          JOptionPane.showMessageDialog(panel, messageToShow,"connection message",JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(connectButton);
    }
    
    private void updateConnection() {
        setIP(ipField.getText());
        setPort(portField.getText());
        
        
    }

    private String getIP() {
        return this.IP;
    }

    private void setIP(String ip) {
        this.IP = ip;
    }

    private String getPort() {
        return this.Port;
    }

    private void setPort(String port) {
        this.Port = port;
    }

private void startModbusServer()
{
    String ip = getIP();
    String port = getPort();

    // ModbusTcpServer modbusServer = new ModbusTcpServer();
        
}


    @Override
    public void generateScript(ScriptWriter writer) {
        writer.appendLine("global socket_ip = \"" + getIP() + "\"");
        writer.appendLine("global socket_port = \"" + getPort() + "\"");
        writer.appendLine("global received_order = \"\"");
        // setup Modbus Tcp connection 
        // writer.appendLine("modbus_add_signal");
      
      
        InputStream is = getClass().getResourceAsStream("/socketConnection.urscript");
        try {
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    writer.appendLine(line);
                }
                br.close();
            } else {
                writer.appendLine("textmsg(\"Error: socketConnection.urscript file not found\")");
            }
        } catch (IOException e) {
            writer.appendLine("textmsg(\"Error reading socketConnection.urscript file: " + e.getMessage() + "\")");
        }
        
    }
}
