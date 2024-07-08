package com.TulipTechnologies.parista_robot.impl;

//import com.ur.urcap.api.contribution.ContributionProvider;
//import com.TulipTechnologies.parista_robot.impl.MqttClientService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration; 



public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration; // Reference to hold service registration

    @Override
    public void start(BundleContext context) throws Exception {
    	MqttClientService service = new MqttClientService(context);
        serviceRegistration = context.registerService(MqttClientService.class, service, null);  
        }

    @Override
    public void stop(BundleContext context) throws Exception {
        // No specific actions required for stop method in this example
    }
}