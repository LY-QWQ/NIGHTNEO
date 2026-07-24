package com.opennight.modules.impl.movement;

import com.opennight.event.impl.SneakEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.event.EventTarget;

public class NoPush
extends Module {
    public NoPush() {
        super("NoPush", Category.MOVEMENT);
    }

    @EventTarget
    public void onSneak(SneakEvent sneakEvent) {
        if (!FireballBlink.INSTANCE.isEnabled()) {
            sneakEvent.setCancelled(true);
        }
    }
}