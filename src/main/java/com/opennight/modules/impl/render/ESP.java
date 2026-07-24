package com.opennight.modules.impl.render;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.RenderEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.BooleanSetting;
import com.opennight.settings.impl.ModeSetting;
import com.opennight.settings.impl.NumberSetting;
import com.opennight.utils.render.ColorUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import java.awt.Color;

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
        PoseStack pose = event.poseStack();
        float pt = event.partialTick();
        Color c = new Color(red.getValue().intValue(), green.getValue().intValue(), blue.getValue().intValue());

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

            pose.pushPose();
            RenderSystem.disableDepthTest();
            RenderSystem.lineWidth(lineWidth.getValue().floatValue());

            if (mode.is("Box") || mode.is("Wireframe")) {
                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                Tesselator t = Tesselator.getInstance();
                BufferBuilder b = t.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
                Matrix4f mat = pose.last().pose();
                int col = c.getRGB();
                // bottom face
                b.addVertex(mat, (float)minX, (float)minY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)minY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)minY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)minY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)minY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)minY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)minY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)minY, (float)minZ).setColor(col);
                // top face
                b.addVertex(mat, (float)minX, (float)maxY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)maxY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)maxY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)maxY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)maxY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)maxY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)maxY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)maxY, (float)minZ).setColor(col);
                // pillars
                b.addVertex(mat, (float)minX, (float)minY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)maxY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)minY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)maxY, (float)minZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)minY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)maxX, (float)maxY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)minY, (float)maxZ).setColor(col);
                b.addVertex(mat, (float)minX, (float)maxY, (float)maxZ).setColor(col);
                BufferUploader.drawWithShader(b.buildOrThrow());
            }

            if (mode.is("2D")) {
                String name = p.getName().getString();
                Vec3 proj = p.getPosition(pt).add(0, p.getBbHeight() + 0.3, 0).subtract(cam);
                float x = (float) (mc.getWindow().getGuiScaledWidth() / 2f + proj.x);
                float y = (float) (mc.getWindow().getGuiScaledHeight() / 2f - proj.y);
                mc.font.drawShadow(pose, "\u00a7c" + name, x - mc.font.width(name) / 2f, y - 5, ColorUtil.WHITE);
            }

            RenderSystem.enableDepthTest();
            pose.popPose();
        }
    }
}
