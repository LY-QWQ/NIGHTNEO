package com.opennight.modules.impl.movement;
import com.opennight.event.EventTarget;
import com.opennight.event.impl.TickEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
public class Scaffold extends Module {
    private final NumberSetting delay = new NumberSetting("Delay", 0, 0, 300, 10);
    public Scaffold() { super("Scaffold", Category.MOVEMENT); }
    @EventTarget
    public void onTick(TickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        if (!(mc.player.getMainHandItem().getItem() instanceof BlockItem)) return;
        BlockPos playerPos = mc.player.blockPosition();
        BlockPos target = null;
        for (BlockPos p : new BlockPos[]{playerPos.below(), playerPos.below().north(), playerPos.below().south(), playerPos.below().east(), playerPos.below().west()}) {
            if (mc.level.getBlockState(p).isAir()) { target = p; break; }
        }
        if (target == null) return;
        if (delay.getValue().intValue() > 0) { try { Thread.sleep(delay.getValue().intValue() / 2); } catch (Exception ignored) {} }
        BlockHitResult hit = new BlockHitResult(Vec3.atCenterOf(target), Direction.UP, target, false);
        mc.gameMode.useItemOn(mc.player, InteractionHand.MAIN_HAND, hit);
        mc.player.swing(InteractionHand.MAIN_HAND);
    }
}
