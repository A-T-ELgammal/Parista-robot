package com.TulipTechnologies.parista_robot.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ur.urcap.api.contribution.ProgramNodeService;


public class Activator implements BundleActivator {

    private MqttClientProgramNodeContributionService mqttService;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Activator of mqtt started!");
        context.registerService(ProgramNodeService.class, new MqttClinetProgramNodeService(), null);
        }

    @Override
    public void stop(BundleContext context) throws Exception {
        if(mqttService != null)
        {
            mqttService.closeConnection();
        }
    }
}