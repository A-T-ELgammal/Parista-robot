package com.TulipTechnologies.parista_robot.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class SwingParistaProgramContributionNodeService implements ProgramNodeContribution{

    @Override
    public void openView() {
        
    }

    @Override
    public void closeView() {
        
    }

    @Override
    public String getTitle() {
        return "Parista-Robot-Cell";
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public void generateScript(ScriptWriter writer) {
        InputStream is = getClass().getResourceAsStream("/Parista_Robot.urscript");
        try
        {
        if(is != null)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null)
            {
                writer.appendLine(line);
            }
            br.close();
        }else{
            writer.generateScript();
        }
    }catch(IOException e)
    {
        writer.appendLine("textmsg(\"Error reading socketConnection.urscript file: " + e.getMessage() + "\")");
    }
    }
    
}
