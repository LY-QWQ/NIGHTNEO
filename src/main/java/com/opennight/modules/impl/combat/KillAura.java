package com.opennight.modules.impl.combat;
import com.opennight.event.EventTarget;
import com.opennight.event.impl.TickEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
public class KillAura extends Module {
    private final NumberSetting range = new NumberSetting("Range", 3.5, 1, 6, 0.1);
    private final NumberSetting minAps = new NumberSetting("Min APS", 9, 1, 20, 1);
    private final NumberSetting maxAps = new NumberSetting("Max APS", 12, 1, 20, 1);
    private float attacks;
    public KillAura() { super("KillAura", Category.COMBAT); }
    @EventTarget
    public void onTick(TickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.screen != null) return;
        Player target = null;
        double closest = range.getValue().doubleValue();
        for (Player p : mc.level.players()) {
            if (p == mc.player || !p.isAlive()) continue;
            double d = mc.player.distanceTo(p);
            if (d < closest) { closest = d; target = p; }
        }
        if (target == null) return;
        double aps = minAps.getValue().doubleValue() + Math.random() * (maxAps.getValue().doubleValue() - minAps.getValue().doubleValue());
        attacks += (float)(aps / 20.0);
        while (attacks >= 1.0f) {
            mc.gameMode.attack(mc.player, target);
            mc.player.swing(InteractionHand.MAIN_HAND);
            attacks -= 1.0f;
        }
    }
}
