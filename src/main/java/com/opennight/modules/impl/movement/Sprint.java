package com.opennight.modules.impl.movement;

import java.util.HashMap;

import com.opennight.event.impl.MotionEvent;
import net.minecraft.client.KeyMapping;
import com.opennight.event.impl.RotationEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.modules.impl.player.InventoryManager;
import com.opennight.event.EventTarget;

public class Sprint
extends Module {
    // private final HashMap<String, String> keyMappings = new HashMap<>();
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
        this.setEnabled(true);
    }

    @EventTarget
    public void onRotation(RotationEvent rotationEvent) {
        if (InventoryManager.isPerformingAction) {
            return;
        }
        mc.options.toggleSprint().set(false);
        KeyMapping.set(mc.options.keySprint.getKey(), true);
    }
}