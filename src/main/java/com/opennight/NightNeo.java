package com.opennight;

import com.opennight.event.EventBus;
import com.opennight.manager.ModuleManager;
import com.opennight.module.impl.combat.KillAura;
import com.opennight.module.impl.misc.AutoClicker;
import com.opennight.module.impl.movement.Fly;
import com.opennight.module.impl.movement.Sprint;
import com.opennight.module.impl.render.FullBright;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("night")
public class NightNeo {
    public static final Logger LOG = LoggerFactory.getLogger("OpenNIGHT");
    public static final EventBus eventBus = new EventBus();
    public static final ModuleManager moduleManager = new ModuleManager();
    public static NightNeo instance;

    public NightNeo(IEventBus modBus) {
        instance = this;
        LOG.info("OpenNIGHT v1.0-Neo loading on MC 1.21.8");
        new NightEventHandler();
        registerModules();
        LOG.info("OpenNIGHT loaded {} modules", moduleManager.getModules().size());
    }

    private void registerModules() {
        moduleManager.register(new KillAura());
        moduleManager.register(new AutoClicker());
        moduleManager.register(new Fly());
        moduleManager.register(new Sprint());
        moduleManager.register(new FullBright());
    }
}
