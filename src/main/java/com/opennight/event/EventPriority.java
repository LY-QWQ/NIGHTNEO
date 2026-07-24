package com.opennight.event;

public enum EventPriority {
    LOWEST(0),
    LOW(1),
    NORMAL(2),
    HIGH(3),
    HIGHEST(4);

    public final int value;

    EventPriority(int v) {
        this.value = v;
    }
}
