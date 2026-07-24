package com.opennight;

import com.opennight.event.EventBus;
import com.opennight.manager.CommandManager;
import com.opennight.manager.ConfigManager;
import com.opennight.manager.HudManager;
import com.opennight.manager.LagManager;
import com.opennight.manager.ModuleManager;
import com.opennight.manager.TargetManager;
import com.opennight.utils.rotation.Rotation;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.Minecraft;

@Mod("night")
public class NightNeo {
    public static final Logger LOG = LoggerFactory.getLogger("NIGHTNEO");
    public static final EventBus eventBus = new EventBus();
    public static NightNeo instance;
    public static boolean isMCPMapped = false;
    public static String username = "";

    private Rotation rotation;
    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private ConfigManager configManager;
    private HudManager hudManager;
    private LagManager lagManager;
    private TargetManager targetManager;
    private boolean moduleInit = false;

    public static boolean isReady() {
        Minecraft mc = Minecraft.getInstance();
        return instance != null
                && mc.player != null
                && mc.player.tickCount > 5;
    }

    public static NightNeo getInstance() { return instance; }
    public EventBus getEventBus() { return eventBus; }
    public Rotation getRotation() { return rotation; }
    public ModuleManager getModuleManager() { return moduleManager; }
    public CommandManager getCommandManager() { return commandManager; }
    public ConfigManager getConfigManager() { return configManager; }
    public HudManager getHudManager() { return hudManager; }
    public LagManager getLagManager() { return lagManager; }
    public TargetManager getTargetManager() { return targetManager; }

    public void onTick() {
        if (isReady() && !moduleInit) {
            moduleInit = true;
            moduleManager.initModules();
            configManager.initConfigs();
            configManager.loadAll();
        }
    }

    public void shutdown() {
        if (configManager != null) {
            configManager.saveAll();
        }
    }

    public NightNeo(IEventBus modBus) {
        instance = this;
        username = System.getProperty("user.name", "Player");
        ClientBase.mc = Minecraft.getInstance();
        LOG.info("NIGHTNEO v1.0 loading on MC 1.21.8");
        rotation = new Rotation();
        eventBus.register(rotation);
        moduleManager = new ModuleManager();
        hudManager = new HudManager();
        commandManager = new CommandManager();
        configManager = new ConfigManager();
        lagManager = new LagManager();
        targetManager = new TargetManager();
        eventBus.register(hudManager);
        eventBus.register(lagManager);
        eventBus.register(targetManager);
        commandManager.initCommands();
        new NightEventHandler();
    }
}
