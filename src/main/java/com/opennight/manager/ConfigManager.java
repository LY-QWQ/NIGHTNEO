package com.opennight.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.opennight.config.Config;
import com.opennight.config.ModulesConfig;
import com.opennight.config.ValuesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class ConfigManager {
    private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);
    public static final File CONFIG_DIR = new File(System.getProperty("user.home"), ".neonight");
    private final List<Config> configs = new ArrayList<>();

    public ConfigManager() {
        if (!CONFIG_DIR.exists()) CONFIG_DIR.mkdirs();
    }

    public void initConfigs() {
        this.configs.add(new ModulesConfig());
        this.configs.add(new ValuesConfig());
    }

    public void loadAll() {
        for (Config config : this.configs) {
            File file = config.getFile();
            if (!file.exists()) continue;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                config.read(br);
            } catch (IOException e) {
                LOGGER.error("Failed to load config {}", config.getName(), e);
            }
        }
    }

    public void saveAll() {
        for (Config config : this.configs) {
            File file = config.getFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                config.save(bw);
            } catch (IOException e) {
                LOGGER.error("Failed to save config {}", config.getName(), e);
            }
        }
    }
}
