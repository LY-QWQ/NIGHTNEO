package com.opennight.command.impl;

import com.opennight.NightNeo;
import com.opennight.command.Command;
import com.opennight.utils.misc.ChatUtil;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("config", new String[]{"cfg", "conf"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length < 1) {
            ChatUtil.print("Usage: .config <save|load>");
            return;
        }
        String action = args[0].toLowerCase();
        switch (action) {
            case "save" -> {
                NightNeo.getInstance().getConfigManager().saveAll();
                ChatUtil.print("Config saved.");
            }
            case "load" -> {
                NightNeo.getInstance().getConfigManager().loadAll();
                ChatUtil.print("Config loaded.");
            }
            default -> ChatUtil.print("Usage: .config <save|load>");
        }
    }

    @Override
    public String[] onTab(String[] args) {
        if (args.length <= 1) return new String[]{"save", "load"};
        return new String[0];
    }
}
