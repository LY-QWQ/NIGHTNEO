package com.opennight.module.impl.movement;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.TickEvent;
import com.opennight.module.Category;
import com.opennight.module.Module;
import com.opennight.setting.NumberSetting;
import com.opennight.setting.ModeSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import java.util.Random;

public class Scaffold extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "Normal", "Tower", "Fast");
    private final NumberSetting delay = new NumberSetting("Delay", 0, 0, 300, 10);
    private long lastPlace;
    private final Random random = new Random();

    public Scaffold() {
        super("Scaffold", Category.MOVEMENT);
    }

    @Override
    protected void onEnable() {
        lastPlace = 0;
    }

    @EventTarget
    public void onTick(TickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        if (!(mc.player.getMainHandItem().getItem() instanceof BlockItem)) return;

        // Sneak on edge
        BlockPos below = mc.player.blockPosition().below();
        mc.options.keyShift.setDown(!mc.level.getBlockState(below).isSolid());

        // Tower mode
        if (mode.getValue().equals("Tower") && mc.options.keyJump.isDown()) {
            BlockPos towerAt = mc.player.blockPosition();
            if (mc.level.getBlockState(towerAt).isAir()) {
                placeBlock(mc, towerAt);
                mc.player.jumpFromGround();
                return;
            }
        }

        // Find block to place
        BlockPos target = findPlaceTarget(mc);
        if (target == null) return;

        // Delay check
        if (delay.getValue().intValue() > 0) {
            long now = System.currentTimeMillis();
            if (lastPlace > 0 && now - lastPlace < delay.getValue().intValue() + random.nextInt(80)) return;
            lastPlace = now;
        }

        placeBlock(mc, target);
    }

    private BlockPos findPlaceTarget(Minecraft mc) {
        BlockPos playerPos = mc.player.blockPosition();
        BlockPos[] check = {
            playerPos.below(), playerPos.below().north(), playerPos.below().south(),
            playerPos.below().east(), playerPos.below().west()
        };
        for (BlockPos pos : check) {
            if (mc.level.getBlockState(pos).isAir() || mc.level.getBlockState(pos).canBeReplaced()) {
                BlockPos below2 = pos.below();
                if (mc.level.getBlockState(below2).isSolid()) return pos;
            }
        }
        return null;
    }

    private void placeBlock(Minecraft mc, BlockPos pos) {
        Direction face = getPlaceFace(mc, pos);
        if (face == null) return;
        BlockHitResult hit = new BlockHitResult(
            net.minecraft.world.phys.Vec3.atCenterOf(pos).add(face.getStepX() * 0.5, face.getStepY() * 0.5, face.getStepZ() * 0.5),
            face.getOpposite(), pos, false);
        mc.gameMode.useItemOn(mc.player, InteractionHand.MAIN_HAND, hit);
        mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private Direction getPlaceFace(Minecraft mc, BlockPos pos) {
        HitResult hit = mc.hitResult;
        if (hit instanceof BlockHitResult bhr && bhr.getBlockPos().equals(pos)) {
            return bhr.getDirection();
        }
        return Direction.UP;
    }
}
