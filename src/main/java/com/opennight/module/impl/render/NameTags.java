package com.opennight.module.impl.render;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.Render2DEvent;
import com.opennight.module.Category;
import com.opennight.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public class NameTags extends Module {
    public NameTags() {
        super("NameTags", Category.RENDER);
    }

    @EventTarget
    public void onRender2D(Render2DEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        GuiGraphics g = e.graphics;

        for (Player p : mc.level.players()) {
            if (p == mc.player) continue;
            double x = p.getX() - mc.getEntityRenderDispatcher().camera.getPosition().x;
            double y = p.getY() + p.getBbHeight() + 0.5 - mc.getEntityRenderDispatcher().camera.getPosition().y;
            double z = p.getZ() - mc.getEntityRenderDispatcher().camera.getPosition().z;

            // Simple 3D-to-2D projection
            // In 1.21.8 use guiGraphics for drawing
            int dist = (int)mc.player.distanceTo(p);
            String text = p.getName().getString() + " [" + dist + "m]";
            // Skip complex 3D math - just a placeholder
            g.drawString(mc.font, text, 10, 10 + p.getId() % 20 * 15, 0xFFFFFF);
        }
    }
}
