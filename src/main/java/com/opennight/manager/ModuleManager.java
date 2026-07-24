package com.opennight.manager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import com.opennight.NightNeo;
import com.opennight.event.impl.KeyEvent;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.modules.impl.combat.KillAura;
import com.opennight.modules.impl.exploit.FastPlace;
import com.opennight.modules.impl.misc.SafeWalk;
import com.opennight.modules.impl.movement.Scaffold;
import com.opennight.modules.impl.movement.Sprint;
import com.opennight.modules.impl.render.ClickGuiModule;
import com.opennight.modules.impl.render.ESP;
import com.opennight.modules.impl.render.FullBright;
import com.opennight.modules.impl.render.NameTags;
import com.opennight.modules.impl.render.NoFov;
import com.opennight.modules.impl.render.NoHurtCam;
import com.opennight.modules.impl.world.AntiStaff;
import com.opennight.modules.impl.world.Teams;
import com.opennight.event.EventTarget;
import net.minecraft.client.Minecraft;

public class ModuleManager {
    private final Map<String, Module> moduleMap = new ConcurrentHashMap<>();

    public ModuleManager() {
        NightNeo.getInstance().getEventBus().register(this);
    }

    public void initModules() {
        this.register(new KillAura());
        this.register(new FastPlace());
        this.register(new SafeWalk());
        this.register(new Scaffold());
        this.register(new Sprint());
        this.register(new ClickGuiModule());
        this.register(new ESP());
        this.register(new FullBright());
        this.register(new NameTags());
        this.register(new NoFov());
        this.register(new NoHurtCam());
        this.register(new AntiStaff());
        this.register(new Teams());
    }

    public void register(Module module) {
        this.moduleMap.put(module.getClass().getSimpleName(), module);
        module.registerSettings();
    }

    public Module getModule(String string) {
        Module module = null;
        for (Module m : this.moduleMap.values()) {
            if (StringUtils.replace(m.getName(), " ", "").equalsIgnoreCase(string)) {
                module = m;
                break;
            }
        }
        if (module == null) throw new ModuleNotFoundException();
        return module;
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<T> clazz) {
        Module module = this.moduleMap.get(clazz.getSimpleName());
        if (module == null) throw new ModuleNotFoundException();
        return (T) module;
    }

    public List<Module> getModules() {
        return List.copyOf(this.moduleMap.values());
    }

    public List<Module> getModulesByCategory(Category category) {
        return this.moduleMap.values().stream()
                .filter(m -> m.getCategory().equals(category))
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
    }

    @EventTarget
    public void onKey(KeyEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == null) {
            for (Module m : this.moduleMap.values()) {
                if (m.getKey() != 0 && m.getKey() == event.getKeyCode() && event.isPressed()) {
                    m.toggle();
                }
            }
        }
    }
}
