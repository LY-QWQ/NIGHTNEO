package com.opennight.command.impl;

import com.opennight.NightNeo;
import com.opennight.command.Command;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.Module;
import com.opennight.utils.misc.ChatUtil;

public class BindCommand extends Command {
    public BindCommand() {
        super("bind", new String[]{"b", "keybind"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length < 1) {
            ChatUtil.print("Usage: .bind <module> <key>");
            return;
        }
        try {
            ModuleManager mm = NightNeo.getInstance().getModuleManager();
            Module module = mm.getModule(args[0]);
            int key = 0;
            if (args.length >= 2) {
                key = args[1].codePointAt(0);
            }
            module.setKey(key);
            ChatUtil.print("Bound " + module.getName());
        } catch (ModuleNotFoundException e) {
            ChatUtil.print("Module not found: " + args[0]);
        }
    }

    @Override
    public String[] onTab(String[] args) {
        return new String[0];
    }
}
