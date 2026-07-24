package com.opennight.modules.impl.render;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.RenderEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.BooleanSetting;
import com.opennight.settings.impl.NumberSetting;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class NameTags extends Module {
    private final NumberSetting scale = new NumberSetting("Scale", 1.0, 0.5, 2.0, 0.1);
    private final BooleanSetting showHealth = new BooleanSetting("Health", true);

    public NameTags() { super("NameTags", Category.RENDER); }

    @EventTarget
    public void onRender(RenderEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        PoseStack pose = event.poseStack();
        float pt = event.partialTick();
        Font font = mc.font;

        for (Player player : mc.level.players()) {
            if (player == mc.player || player.isSpectator()) continue;
            Vec3 pos = player.getPosition(pt).add(0, player.getBbHeight() + 0.6, 0);
            Vec3 cam = mc.gameRenderer.getMainCamera().getPosition();

            pose.pushPose();
            pose.translate(pos.x - cam.x, pos.y - cam.y, pos.z - cam.z);
            pose.mulPose(mc.gameRenderer.getMainCamera().rotation());
            float s = -0.025f * scale.getValue().floatValue();
            pose.scale(s, s, s);

            String text = player.getName().getString();
            if (showHealth.getValue()) text += " \u00a7c" + (int) player.getHealth();

            font.drawInBatch(text, -font.width(text) / 2f, 0, 0xFFFFFFFF, false, pose.last().pose(),
                    mc.renderBuffers().bufferSource(), Font.DisplayMode.SEE_THROUGH, 0x40000000, 0xF000F0);
            pose.popPose();
        }
    }
}
