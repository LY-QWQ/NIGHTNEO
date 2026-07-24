package com.opennight.event.impl;

import lombok.*;
import net.minecraft.world.entity.Entity;
import com.opennight.event.EventMarker;

import java.util.Objects;

@Data
@AllArgsConstructor
public class RayTraceEvent
implements EventMarker {
    @Getter @Setter
    public Entity entity;
    @Getter @Setter
    public float yaw;
    @Getter @Setter
    public float pitch;
}