package com.opennight.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.Generated;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import com.opennight.ClientBase;
import com.opennight.NightNeo;
import com.opennight.event.impl.GameTickEvent;
import com.opennight.modules.impl.combat.KillAura;
import com.opennight.utils.game.RotationUtil;
import com.opennight.event.EventTarget;

public class TargetManager
extends ClientBase {
    public static TargetManager INSTANCE;
    private final List<LivingEntity> targets = new ArrayList<>();

    public TargetManager() {
        INSTANCE = this;
    }

    @EventTarget
    public void onGameTick(GameTickEvent gameTickEvent) {
        if (mc.level == null) {
            return;
        }
        if (KillAura.INSTANCE == null) {
            return;
        }
        if (!NightNeo.isReady()) {
            return;
        }
        this.targets.clear();
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (!(entity instanceof LivingEntity livingEntity)) continue;
            if (!KillAura.INSTANCE.isValidTarget(entity)) continue;
            this.targets.add(livingEntity);
        }
    }

    public Stream<LivingEntity> getTargetsStream(float range) {
        if (mc.player == null) {
            return Stream.empty();
        }
        return new ArrayList<>(this.targets).stream().filter(livingEntity -> {
            Vec3 closest = RotationUtil.closestPoint(mc.player.getEyePosition(), livingEntity.getBoundingBox());
            return closest.distanceTo(mc.player.getEyePosition()) <= (double)range;
        });
    }

    public List<LivingEntity> getTargets(float range) {
        return this.getTargetsStream(range).toList();
    }

    @Generated
    public List<LivingEntity> getTargetList() {
        return this.targets;
    }
}