package com.TulipTechnologies.SampleMoveURCap.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;
import com.ur.urcap.api.domain.data.DataModel;

public class SocketInstallationProgramNodeService implements SwingInstallationNodeService<SocketInstallationNodeContribution, SwingInstallationNodeView<SocketInstallationNodeContribution>> {

    @Override
    public void configureContribution(ContributionConfiguration configuration) {
    }

    @Override
    public String getTitle(Locale locale) {
        return "(robot - device) connection!";
    }

    @Override
    public SwingInstallationNodeView<SocketInstallationNodeContribution> createView(ViewAPIProvider apiProvider) 
    {
        return new SocketInstallationNodeView();
    }

    @Override
    public SocketInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
            SwingInstallationNodeView<SocketInstallationNodeContribution> view, DataModel model,
            CreationContext context) {
                return new SocketInstallationNodeContribution(model);
    }

    
}
