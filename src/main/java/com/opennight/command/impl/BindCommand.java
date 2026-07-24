package com.opennight.command.impl;

import com.opennight.NightNeo;
import com.opennight.command.Command;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.Module;
import com.opennight.utils.misc.ChatUtil;

public class BindCommand extends Command {
    public BindCommand() {
        super("bind", "b", "keybind");
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
                key = org.lwjgl.glfw.GLFW.glfwGetKeyScancode(
                    Integer.parseInt(args[1].toUpperCase().startsWith("KEY_") ? args[1].toUpperCase() : "KEY_" + args[1].toUpperCase())
                );
                if (key < 0) {
                    ChatUtil.print("Invalid key: " + args[1]);
                    return;
                }
            }
            module.setKey(key);
            ChatUtil.print("Bound " + module.getName() + " to key " + args[1]);
        } catch (ModuleNotFoundException e) {
            ChatUtil.print("Module not found: " + args[0]);
        }
    }
}
