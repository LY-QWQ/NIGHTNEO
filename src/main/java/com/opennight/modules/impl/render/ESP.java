package com.opennight.modules.impl.render;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.RenderEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.BooleanSetting;
import com.opennight.settings.impl.ModeSetting;
import com.opennight.settings.impl.NumberSetting;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ESP extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "Box", "Wireframe", "2D");
    private final NumberSetting lineWidth = new NumberSetting("LineWidth", 1.5, 0.5, 5, 0.5);
    private final BooleanSetting players = new BooleanSetting("Players", true);
    private final NumberSetting red = new NumberSetting("Red", 255, 0, 255, 1);
    private final NumberSetting green = new NumberSetting("Green", 0, 0, 255, 1);
    private final NumberSetting blue = new NumberSetting("Blue", 0, 0, 255, 1);

    public ESP() { super("ESP", Category.RENDER); }

    @EventTarget
    public void onRender(RenderEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        Color c = new Color(red.getValue().intValue(), green.getValue().intValue(), blue.getValue().intValue());
        float pt = event.partialTick();

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (!(entity instanceof Player p) || p == mc.player || p.isSpectator() || !players.getValue()) continue;

            Vec3 cam = mc.gameRenderer.getMainCamera().getPosition();
            Vec3 lerp = new Vec3(
                p.xOld + (p.getX() - p.xOld) * pt,
                p.yOld + (p.getY() - p.yOld) * pt,
                p.zOld + (p.getZ() - p.zOld) * pt
            );
            AABB bb = p.getBoundingBox().move(lerp.subtract(p.position()));
            double minX = bb.minX - cam.x, minY = bb.minY - cam.y, minZ = bb.minZ - cam.z;
            double maxX = bb.maxX - cam.x, maxY = bb.maxY - cam.y, maxZ = bb.maxZ - cam.z;

            if (mode.is("2D")) {
                // Simple 2D ESP tag
                Vec3 pos = p.getPosition(pt).add(0, p.getBbHeight() + 0.3, 0).subtract(cam);

                // Use GuiGraphics-based approach via Render2DEvent instead
            }
        }
    }
}
