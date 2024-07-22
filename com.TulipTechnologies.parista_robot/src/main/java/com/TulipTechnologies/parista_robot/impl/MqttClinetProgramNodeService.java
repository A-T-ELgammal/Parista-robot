package com.TulipTechnologies.parista_robot.impl;

import java.io.InputStream;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHTML'");
    }
    
    @Override
    public ProgramNodeContribution createNode(URCapAPI api, DataModel model) {
        return new MqttClientProgramNodeContributionService(api, model);    
    }
    
}
