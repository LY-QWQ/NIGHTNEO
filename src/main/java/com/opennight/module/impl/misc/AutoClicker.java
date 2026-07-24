package com.opennight.module.impl.misc;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.TickEvent;
import com.opennight.module.Category;
import com.opennight.module.Module;
import com.opennight.setting.NumberSetting;
import net.minecraft.client.Minecraft;

public class AutoClicker extends Module {
    private final NumberSetting minCps = new NumberSetting("Min CPS", 8, 1, 20, 1);
    private final NumberSetting maxCps = new NumberSetting("Max CPS", 12, 1, 20, 1);
    private float attacks;

    public AutoClicker() {
        super("AutoClicker", Category.MISC);
    }

    @EventTarget
    public void onTick(TickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.screen != null) return;
        if (!mc.options.keyAttack.isDown()) return;

        double aps = minCps.getValue().doubleValue() + Math.random() * (maxCps.getValue().doubleValue() - minCps.getValue().doubleValue());
        attacks += (float)(aps / 20.0);
        while (attacks >= 1.0f) {
            mc.startAttack();
            attacks -= 1.0f;
        }
    }
}
