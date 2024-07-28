package com.TulipTechnologies.parista_robot.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.ProgramNodeService;
import com.ur.urcap.api.domain.URCapAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class MqttClinetProgramNodeService implements ProgramNodeService {
    
    @Override
    public String getId() {
        return "Starting MQTT Client!";
    }

    @Override
    public boolean isDeprecated() {
        return false;
    }

    @Override
    public boolean isChildrenAllowed() {
        return false;
    }

    @Override
    public String getTitle() {
        return "MQTT Client Program <_>";
    }

    @Override
    public InputStream getHTML() {
       String htmlContent = "<html>" +
                             "<head>" +
                             "<title>Parista Robot!</title>" +
                             "</head>" +
                             "<body>" +
                             "<h3>Connect MQTT server with robot </h3>" +
                             "</body>" +
                             "</html>";
        InputStream htmlStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
        return  htmlStream;

    }
    
    @Override
    public ProgramNodeContribution createNode(URCapAPI api, DataModel model) {
        return new MqttClientProgramNodeContributionService(api, model);    
    }
    
}
