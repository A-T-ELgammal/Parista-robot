package com.TulipTechnologies.SampleMoveURCap.impl;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;

public class SocketInstallationNodeView implements SwingInstallationNodeView<SocketInstallationNodeContribution> {

    @Override
    public void buildUI(JPanel panel, SocketInstallationNodeContribution contribution) {
        contribution.buildUI(panel);
    }
}