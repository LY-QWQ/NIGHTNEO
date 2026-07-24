package com.opennight;

import com.opennight.modules.impl.combat.KillAura;
import com.opennight.modules.impl.movement.Scaffold;
import com.opennight.modules.impl.movement.Sprint;
import com.opennight.modules.impl.misc.SafeWalk;
import com.opennight.modules.impl.render.ClickGuiModule;
import com.opennight.modules.impl.render.NameTags;
import com.opennight.modules.impl.render.FullBright;
import com.opennight.modules.impl.render.NoFov;
import com.opennight.modules.impl.render.NoHurtCam;
import com.opennight.modules.impl.world.AntiStaff;
import com.opennight.modules.impl.exploit.FastPlace;
import com.opennight.event.EventBus;
import com.opennight.manager.ModuleManager;
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
    public static boolean isMCPMapped = false;

    public static boolean isReady() { return instance != null; }
    public EventBus getEventBus() { return eventBus; }

    public NightNeo(IEventBus modBus) {
        instance = this;
        LOG.info("OpenNIGHT v1.0-Neo loading on MC 1.21.8");
        new NightEventHandler();
        registerModules();
        LOG.info("OpenNIGHT loaded {} modules", moduleManager.getModules().size());
    }

        private void registerModules() {
        moduleManager.register(new KillAura());
        moduleManager.register(new Scaffold());
        moduleManager.register(new ClickGuiModule());
        moduleManager.register(new NameTags());
        moduleManager.register(new FullBright());
        moduleManager.register(new Sprint());
        moduleManager.register(new SafeWalk());
        moduleManager.register(new NoFov());
        moduleManager.register(new NoHurtCam());
        moduleManager.register(new AntiStaff());
        moduleManager.register(new FastPlace());
    }
}
