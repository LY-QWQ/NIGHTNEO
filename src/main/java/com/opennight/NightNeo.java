package com.opennight;

import com.opennight.event.EventBus;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.impl.combat.AntiKB;
import com.opennight.modules.impl.combat.Critical;
import com.opennight.modules.impl.combat.KillAura;
import com.opennight.modules.impl.exploit.Disabler;
import com.opennight.modules.impl.misc.AutoClicker;
import com.opennight.modules.impl.movement.Fly;
import com.opennight.modules.impl.movement.GameTimer;
import com.opennight.modules.impl.movement.NoDelay;
import com.opennight.modules.impl.movement.NoSlow;
import com.opennight.modules.impl.movement.Scaffold;
import com.opennight.modules.impl.movement.Sprint;
import com.opennight.modules.impl.movement.TargetStrafe;
import com.opennight.modules.impl.player.ChestStealer;
import com.opennight.modules.impl.player.NoFall;
import com.opennight.modules.impl.render.FullBright;
import com.opennight.modules.impl.render.NameTags;
import com.opennight.modules.impl.render.OldHitting;
import com.opennight.modules.impl.render.XRay;
import com.opennight.modules.impl.world.AutoTools;
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
