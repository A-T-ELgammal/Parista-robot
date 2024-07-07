package com.TulipTechnologies.parista_robot.impl;
// import com.ur.urcap.api.*;
import com.ur.urcap.api.contribution.ContributionProvider;
// import com.ur.urcap.api.contribution.installation.InstallationNodeService;
// import com.ur.urcap.api.contribution.program.ProgramNodeService;
import com.ur.urcap.api.contribution.startup.StartupNodeService;
// import com.ur.urcap.api.contribution.ContributionConfiguration;
// import com.ur.urcap.api.contribution.startup.StartupNodeContribution;
// import com.ur.urcap.api.domain.data.DataModel;
// import com.ur.urcap.api.domain.script.ScriptWriter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.registerService(StartupNodeService.class, new ClientStartupService(), null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Activator says Goodbye World!");
	}
}