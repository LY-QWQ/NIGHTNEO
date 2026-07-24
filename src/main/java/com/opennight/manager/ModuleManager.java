package com.opennight.manager;

import com.opennight.module.Module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void register(Module m) {
        modules.add(m);
    }

    public List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    }

    public Module getModule(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
