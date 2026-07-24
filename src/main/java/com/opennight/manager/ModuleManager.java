package com.opennight.manager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import com.opennight.ClientBase;
import com.opennight.NightNeo;
import com.opennight.event.impl.KeyEvent;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.modules.impl.combat.AntiBots;
import com.opennight.modules.impl.combat.AntiFireball;
import com.opennight.modules.impl.combat.AntiKB;
import com.opennight.modules.impl.combat.AutoOffHand;
import com.opennight.modules.impl.combat.AutoSoup;
import com.opennight.modules.impl.combat.AutoThrow;
import com.opennight.modules.impl.combat.Backtrack;
import com.opennight.modules.impl.combat.Critical;
import com.opennight.modules.impl.combat.CrystalAura;
import com.opennight.modules.impl.combat.FakeLag;
import com.opennight.modules.impl.combat.KillAura;
import com.opennight.modules.impl.exploit.Disabler;
import com.opennight.modules.impl.exploit.FastPlace;
import com.opennight.modules.impl.misc.AimAssist;
import com.opennight.modules.impl.misc.AutoClicker;
import com.opennight.modules.impl.misc.AutoRod;
import com.opennight.modules.impl.misc.KillSay;
import com.opennight.modules.impl.misc.MusicPlayer;
import com.opennight.modules.impl.misc.SafeWalk;
import com.opennight.modules.impl.movement.CollisionSpeed;
import com.opennight.modules.impl.movement.NoSlow;
import com.opennight.modules.impl.movement.FastWeb;
import com.opennight.modules.impl.movement.FireballBlink;
import com.opennight.modules.impl.movement.Fly;
import com.opennight.modules.impl.movement.HighJump;
import com.opennight.modules.impl.movement.NoDelay;
import com.opennight.modules.impl.movement.NoPush;
import com.opennight.modules.impl.movement.Scaffold;
import com.opennight.modules.impl.movement.Sprint;
import com.opennight.modules.impl.movement.TargetStrafe;
import com.opennight.modules.impl.movement.GameTimer;
import com.opennight.modules.impl.player.AntiTNT;
import com.opennight.modules.impl.player.AntiVoid;
import com.opennight.modules.impl.player.AntiWeb;
import com.opennight.modules.impl.player.AutoMLG;
import com.opennight.modules.impl.player.AutoWebPlace;
import com.opennight.modules.impl.player.ChestStealer;
import com.opennight.modules.impl.player.GhostHand;
import com.opennight.modules.impl.player.Helper;
import com.opennight.modules.impl.player.InventoryManager;
import com.opennight.modules.impl.player.MidPearl;
import com.opennight.modules.impl.player.NoFall;
import com.opennight.modules.impl.player.Stuck;
import com.opennight.modules.impl.render.*;
import com.opennight.modules.impl.world.AntiStaff;
import com.opennight.modules.impl.world.AutoPlay;
import com.opennight.modules.impl.world.AutoTools;
import com.opennight.modules.impl.world.BlockIn;
import com.opennight.modules.impl.world.Debugger;
import com.opennight.modules.impl.world.Teams;
import com.opennight.modules.impl.world.Protocol;
import com.opennight.modules.impl.world.WebUI;
import com.opennight.event.EventTarget;

public class ModuleManager extends ClientBase {
    private final Map<String, Module> moduleMap = new ConcurrentHashMap<>();

    public ModuleManager() {
        NightNeo.instance.getEventBus().register(this);
    }

    public void initModules() {
        this.register(new AntiBots());
        this.register(new AntiFireball());
        this.register(new AntiKB());
        this.register(new AutoOffHand());
        this.register(new AutoSoup());
        this.register(new AutoThrow());
        this.register(new Backtrack());
        this.register(new Critical());
        this.register(new CrystalAura());
        this.register(new KillAura());
        this.register(new FakeLag());

        this.register(new Disabler());
        this.register(new FastPlace());

        this.register(new AimAssist());
        this.register(new AutoClicker());
        this.register(new AutoRod());
        this.register(new SafeWalk());
        this.register(new MusicPlayer());
        this.register(new KillSay());

        this.register(new CollisionSpeed());
        this.register(new NoSlow());
        this.register(new FastWeb());
        this.register(new FireballBlink());
        this.register(new Fly());
        this.register(new HighJump());
        this.register(new NoDelay());
        this.register(new NoPush());
        this.register(new Scaffold());
        this.register(new Sprint());
        this.register(new TargetStrafe());
        this.register(new GameTimer());

        this.register(new AntiTNT());
        this.register(new AntiVoid());
        this.register(new AntiWeb());
        this.register(new AutoMLG());
        this.register(new AutoWebPlace());
        this.register(new ChestStealer());
        this.register(new GhostHand());
        this.register(new Helper());
        this.register(new InventoryManager());
        this.register(new MidPearl());
        this.register(new NoFall());
        this.register(new Stuck());

        this.register(new AspectRatio());
        this.register(new ChestESP());
        this.register(new ClickGuiModule());
        this.register(new Compass());
        this.register(new DamageGlow());
        this.register(new ESP());
        this.register(new EntityEditor());
        this.register(new FakeAntiAim());
        this.register(new FullBright());
        this.register(new Interface());
        this.register(new ItemTags());
        this.register(new LyricsModule());
        this.register(new NameProtect());
        this.register(new NameTags());
        this.register(new NoFov());
        this.register(new NoHurtCam());
        this.register(new OldHitting());
        this.register(new Projectiles());
        this.register(new TimeWeather());
        this.register(new Watermark());
        this.register(new XRay());

        this.register(new AntiStaff());
        this.register(new AutoPlay());
        this.register(new AutoTools());
        this.register(new BlockIn());
        this.register(new Debugger());
        this.register(new Teams());
        this.register(new WebUI());
        this.register(new Protocol());
    }

    public void register(Module module) {
        this.moduleMap.put(module.getClass().getSimpleName(), module);
        module.registerSettings();
    }

    public Module getModule(String string) {
        Module module = null;
        for (Module module2 : this.moduleMap.values()) {
            if (!StringUtils.replace(module2.getName(), " ", "").equalsIgnoreCase(string)) continue;
            module = module2;
        }
        if (module == null) {
            throw new ModuleNotFoundException();
        }
        return module;
    }

    public <T extends Module> T getModule(Class<T> clazz) {
        Module module = clazz.cast(this.moduleMap.get(clazz.getSimpleName()));
        if (module == null) {
            throw new ModuleNotFoundException();
        }
        return (T) module;
    }

    public List<Module> getModules() {
        return this.moduleMap.values().stream().toList();
    }

    public List<Module> getModulesByCategory(Category category) {
        return this.moduleMap.values().stream()
                .filter(module -> module.getCategory().equals(category))
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
    }

    @EventTarget
    public void onKey(KeyEvent event) {
        if (mc.screen == null) {
            for (Module module : this.moduleMap.values()) {
                if (module.getKey() != 0 && module.getKey() == event.getKeyCode() && event.isPressed()) {
                    module.toggle();
                }
            }
        }
    }
}
