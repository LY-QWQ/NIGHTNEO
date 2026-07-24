package com.opennight.manager;

import java.util.HashMap;
import java.util.Map;
import com.opennight.NightNeo;
import com.opennight.command.Command;
import com.opennight.command.impl.BindCommand;
import com.opennight.command.impl.ConfigCommand;
import com.opennight.command.impl.ToggleCommand;
import com.opennight.event.impl.ChatEvent;
import com.opennight.utils.misc.ChatUtil;
import com.opennight.event.EventTarget;

public class CommandManager {
    public static final String PREFIX = ".";
    public final Map<String, Command> aliasMap = new HashMap<>();

    public CommandManager() {
        NightNeo.getInstance().getEventBus().register(this);
    }

    public void initCommands() {
        this.registerCommand(new BindCommand());
        this.registerCommand(new ConfigCommand());
        this.registerCommand(new ToggleCommand());
    }

    private void registerCommand(Command command) {
        this.aliasMap.put(command.getPrefix().toLowerCase(), command);
        for (String alias : command.getAliases()) {
            this.aliasMap.put(alias.toLowerCase(), command);
        }
    }

    @EventTarget
    public void onChat(ChatEvent chatEvent) {
        if (chatEvent.getMessage().startsWith(PREFIX)) {
            chatEvent.setCancelled(true);
            String string = chatEvent.getMessage().substring(PREFIX.length());
            String[] stringArray = string.split(" ");
            if (stringArray.length < 1) {
                ChatUtil.print("Unknown command");
                return;
            }
            String alias = stringArray[0].toLowerCase();
            Command command = this.aliasMap.get(alias);
            if (command == null) {
                ChatUtil.print("Unknown command");
                return;
            }
            String[] args = new String[stringArray.length - 1];
            System.arraycopy(stringArray, 1, args, 0, args.length);
            command.onCommand(args);
        }
    }
}
