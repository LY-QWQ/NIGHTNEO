package com.opennight.module;

import com.opennight.event.Event;

public class ModuleToggleEvent extends Event {
    public final Module module;
    public final boolean enabled;

    public ModuleToggleEvent(Module m, boolean e) {
        this.module = m;
        this.enabled = e;
    }
}
