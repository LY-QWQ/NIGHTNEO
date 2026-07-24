package com.opennight.event.impl;

import lombok.*;
import com.opennight.event.EventMarker;

@AllArgsConstructor
@Data
public class UseItemRayTraceEvent
implements EventMarker {
    @Getter @Setter
    private float yaw;
    @Getter @Setter
    private float pitch;
}