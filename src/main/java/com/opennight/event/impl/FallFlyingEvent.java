package com.opennight.event.impl;

import lombok.*;
import com.opennight.event.EventMarker;

@Data
@AllArgsConstructor
public class FallFlyingEvent
implements EventMarker {
    @Getter @Setter
    private float pitch;
}