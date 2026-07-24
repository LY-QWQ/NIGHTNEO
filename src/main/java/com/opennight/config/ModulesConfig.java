package com.opennight.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.opennight.NightNeo;
import com.opennight.config.Config;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.Module;

public class ModulesConfig
extends Config {
    private static final Logger LOGGER = LogManager.getLogger(ModulesConfig.class);

    public ModulesConfig() {
        super("modules.cfg");
    }

    @Override
    public void read(BufferedReader bufferedReader) throws IOException {
        String line;
        ModuleManager moduleManager = NightNeo.instance.getModuleManager();
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(":", 3);
            if (parts.length != 3) {
                LOGGER.error("Failed to read line {}!", line);
                continue;
            }
            String moduleName = parts[0];
            int keyCode = Integer.parseInt(parts[1]);
            boolean enabled = Boolean.parseBoolean(parts[2]);
            try {
                Module module = moduleManager.getModule(moduleName);
                module.setKey(keyCode);
                module.setEnabled(enabled);
            } catch (ModuleNotFoundException ex) {
                LOGGER.error("Failed to find module {}!", moduleName);
            }
        }
    }

    @Override
    public void save(BufferedWriter bufferedWriter) throws IOException {
        ModuleManager moduleManager = NightNeo.instance.getModuleManager();
        ArrayList<Module> moduleList = new ArrayList<>(moduleManager.getModules());
        for (Module module : moduleList) {
            bufferedWriter.write(String.format((String)"%s:%d:%s\n", (Object[])new Object[]{module.getName(), module.getKey(), module.isEnabled()}));
        }
    }
}