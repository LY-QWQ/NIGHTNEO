package com.opennight.event.impl;

import com.opennight.event.EventMarker;
import com.opennight.modules.Module;

public record ModuleToggleEvent(Module module, boolean enabled) implements EventMarker {
}
