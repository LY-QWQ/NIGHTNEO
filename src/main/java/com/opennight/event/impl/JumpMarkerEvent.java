package com.opennight.event.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.opennight.event.EventMarker;

@Data
@AllArgsConstructor
public class JumpMarkerEvent
implements EventMarker {
    private float yaw;
}