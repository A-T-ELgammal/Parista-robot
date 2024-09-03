package com.TulipTechnologies.parista_robot.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class SocketInstallationProgramNodeService implements SwingInstallationNodeService<SocketInstallationNodeContribution, SocketInstallationNodeView> {

    @Override
    public void configureContribution(ContributionConfiguration configuration) {
    }

    @Override
    public String getTitle(Locale locale) {
        return "(robot - device) connection!";
    }



    @Override
    public SocketInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
            SocketInstallationNodeView view, DataModel model, CreationContext context) {
        return new SocketInstallationNodeContribution(model);
            }

    @Override
    public SocketInstallationNodeView createView(ViewAPIProvider apiProvider) {
        return new SocketInstallationNodeView();
    }

    
}
