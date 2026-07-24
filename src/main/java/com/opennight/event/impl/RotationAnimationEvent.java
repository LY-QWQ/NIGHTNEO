package com.opennight.event.impl;

import lombok.*;
import com.opennight.event.EventMarker;

@ToString
@Data
@AllArgsConstructor
public class RotationAnimationEvent
implements EventMarker {
    @Getter @Setter
    private float yaw, lastYaw;
    @Getter @Setter
    private float pitch, lastPitch;
}