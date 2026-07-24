package com.opennight.module.impl.movement;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.TickEvent;
import com.opennight.module.Category;
import com.opennight.module.Module;
import com.opennight.setting.NumberSetting;
import net.minecraft.client.Minecraft;

public class Fly extends Module {
    private final NumberSetting speed = new NumberSetting("Speed", 0.5, 0.1, 2, 0.1);

    public Fly() {
        super("Fly", Category.MOVEMENT);
    }

    @EventTarget
    public void onTick(TickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlyingSpeed(speed.getValue().floatValue());
    }

    @Override
    protected void onDisable() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            if (!mc.player.isCreative() && !mc.player.isSpectator()) {
                mc.player.getAbilities().flying = false;
                mc.player.getAbilities().setFlyingSpeed(0.05f);
            }
        }
    }
}
