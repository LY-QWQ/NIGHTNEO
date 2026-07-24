package com.opennight;

import com.opennight.event.EventBus;
import com.opennight.manager.ModuleManager;
import com.opennight.module.impl.combat.AntiKB;
import com.opennight.module.impl.combat.Critical;
import com.opennight.module.impl.combat.KillAura;
import com.opennight.module.impl.exploit.Disabler;
import com.opennight.module.impl.misc.AutoClicker;
import com.opennight.module.impl.movement.Fly;
import com.opennight.module.impl.movement.GameTimer;
import com.opennight.module.impl.movement.NoDelay;
import com.opennight.module.impl.movement.NoSlow;
import com.opennight.module.impl.movement.Scaffold;
import com.opennight.module.impl.movement.Sprint;
import com.opennight.module.impl.movement.TargetStrafe;
import com.opennight.module.impl.player.ChestStealer;
import com.opennight.module.impl.player.NoFall;
import com.opennight.module.impl.render.FullBright;
import com.opennight.module.impl.render.NameTags;
import com.opennight.module.impl.render.OldHitting;
import com.opennight.module.impl.render.XRay;
import com.opennight.module.impl.world.AutoTools;
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
        // Combat
        moduleManager.register(new KillAura());
        moduleManager.register(new AntiKB());
        moduleManager.register(new Critical());

        // Movement
        moduleManager.register(new Fly());
        moduleManager.register(new Scaffold());
        moduleManager.register(new Sprint());
        moduleManager.register(new NoSlow());
        moduleManager.register(new TargetStrafe());
        moduleManager.register(new NoDelay());
        moduleManager.register(new GameTimer());

        // Player
        moduleManager.register(new NoFall());
        moduleManager.register(new ChestStealer());

        // Render
        moduleManager.register(new NameTags());
        moduleManager.register(new FullBright());
        moduleManager.register(new XRay());
        moduleManager.register(new OldHitting());

        // World
        moduleManager.register(new AutoTools());

        // Misc / Exploit
        moduleManager.register(new AutoClicker());
        moduleManager.register(new Disabler());
    }
}
