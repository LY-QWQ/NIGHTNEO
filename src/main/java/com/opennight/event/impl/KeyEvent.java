package com.opennight.event.impl;

import com.opennight.event.Event;

public class KeyEvent extends Event {
    private final int key;
    private final int action;
    private final int modifiers;

    public KeyEvent(int key, int action, int mods) {
        this.key = key;
        this.action = action;
        this.modifiers = mods;
    }

    public int getKey() {
        return key;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }
}
