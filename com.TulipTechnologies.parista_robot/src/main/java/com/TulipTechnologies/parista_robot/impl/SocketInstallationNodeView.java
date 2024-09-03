package com.TulipTechnologies.parista_robot.impl;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;
public class SocketInstallationNodeView implements SwingInstallationNodeView<SocketInstallationNodeContribution> {

    private static final String IP_KEY = "ip";
    private static final String PORT_KEY = "port";
    private JTextField ipField;
    private JTextField portField;

    private  SocketInstallationNodeContribution contribution;

    @Override
    public void buildUI(JPanel panel,SocketInstallationNodeContribution contribution) {
        this.contribution = contribution;
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

        //ip panel
        JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ipPanel.add(new JLabel("Device IP:"));
        ipField = new JTextField(15);
        ipField.setPreferredSize(new Dimension(200, 25));
        ipField.setText(contribution.getIP());
        ipField.setForeground(java.awt.Color.BLACK);

        ipPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e)
            {
                if(portField.getText().equals("Enter port Number..."))
                {
                    portField.setText("");
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
        
        
        /////////////////////////////////////
        //port panel
        JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        portPanel.add(new JLabel("Port:"));
        portField = new JTextField(15);
        // portField.setPreferredSize(new Dimension(200, 25));
        portField.setText(contribution.getPort());
        portField.setForeground(java.awt.Color.GRAY);
        portPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e)
            {
                if(portField.getText().equals("Enter port Number..."))
                {
                    portField.setText("");
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
          String messageToShow = "device Connected with ip: " + contribution.getIP() + " & port: " + contribution.getPort();
          JOptionPane.showMessageDialog(panel, messageToShow,"connection message",JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(connectButton);
    }

    private void updateConnection() {
    this.contribution.setIP(ipField.getText());
    this.contribution.setPort(portField.getText());   
    }
}