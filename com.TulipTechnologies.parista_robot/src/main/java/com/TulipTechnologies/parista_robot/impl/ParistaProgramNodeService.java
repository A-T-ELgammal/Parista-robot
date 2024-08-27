package com.TulipTechnologies.parista_robot.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class ParistaProgramNodeService implements SwingProgramNodeService<SwingParistaProgramContributionNodeService, SwingParistaProgramNodeView>{

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public void configureContribution(ContributionConfiguration configuration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'configureContribution'");
    }

    @Override
    public String getTitle(Locale locale) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    @Override
    public SwingParistaProgramNodeView createView(ViewAPIProvider apiProvider) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createView'");
    }

    @Override
    public SwingParistaProgramContributionNodeService createNode(ProgramAPIProvider apiProvider,
            SwingParistaProgramNodeView view, DataModel model, CreationContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNode'");
    }
    
}
