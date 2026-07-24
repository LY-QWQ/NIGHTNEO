package com.opennight.event.impl;

import com.opennight.event.Event;

public class TickEvent extends Event {
    public enum Phase { PRE, POST }
    public final Phase phase;

    public TickEvent(Phase p) {
        this.phase = p;
    }
}
