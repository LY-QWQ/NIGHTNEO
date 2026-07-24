package com.opennight.module.impl.movement;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.TickEvent;
import com.opennight.module.Category;
import com.opennight.module.Module;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @EventTarget
    public void onTick(TickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (mc.player.xxa != 0 || mc.player.zza != 0) {
            mc.player.setSprinting(true);
        }
    }
}
