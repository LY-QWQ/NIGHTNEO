package com.opennight.command.impl;

import com.opennight.NightNeo;
import com.opennight.command.Command;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.Module;
import com.opennight.utils.misc.ChatUtil;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", new String[]{"t"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length < 1) {
            ChatUtil.print("Usage: .toggle <module>");
            return;
        }
        try {
            ModuleManager mm = NightNeo.getInstance().getModuleManager();
            Module module = mm.getModule(args[0]);
            module.toggle();
            ChatUtil.print(module.getName() + " is now " + (module.isEnabled() ? "enabled." : "disabled."));
        } catch (ModuleNotFoundException e) {
            ChatUtil.print("Module not found: " + args[0]);
        }
    }

    @Override
    public String[] onTab(String[] args) {
        return new String[0];
    }
}
