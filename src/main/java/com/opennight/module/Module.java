package com.opennight.module;

import com.opennight.NightNeo;

public class Module {
    private final String name;
    private final Category category;
    private boolean enabled;
    private int key;

    public Module(String name, Category cat) {
        this(name, cat, 0);
    }

    public Module(String name, Category cat, int key) {
        this.name = name;
        this.category = cat;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setEnabled(boolean b) {
        if (enabled == b) return;
        enabled = b;
        if (enabled) onEnable();
        else onDisable();
        NightNeo.eventBus.call(new ModuleToggleEvent(this, enabled));
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    protected void onEnable() {
        NightNeo.eventBus.register(this);
    }

    protected void onDisable() {
        NightNeo.eventBus.unregister(this);
    }
}
