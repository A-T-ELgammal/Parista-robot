package com.TulipTechnologies.parista_robot.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;


public class Activator implements BundleActivator {


    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Activator of mqtt started!");
        context.registerService(SwingInstallationNodeService.class, new SocketInstallationProgramNodeService(), null);
        }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}