package com.opennight.modules.impl.player.helper;

import lombok.Getter;
import com.opennight.ClientBase;
import com.opennight.event.impl.MotionEvent;
import com.opennight.event.impl.PreMotionEvent;
import com.opennight.event.impl.RenderEvent;
import com.opennight.event.impl.TickEvent;
import com.opennight.utils.rotation.Rotation;

public abstract class HelperBase
extends ClientBase {
    @Getter
    private final String name;

    public HelperBase(String string) {
        this.name = string;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onTick(TickEvent tickEvent) {
    }

    public void onMotion(MotionEvent motionEvent) {
    }

    public void onRender(RenderEvent renderEvent) {
    }

    public void onPreMotion(PreMotionEvent preMotionEvent) {
    }

    public boolean isActive() {
        return false;
    }

    public Rotation getTargetRotation() {
        return null;
    }

    }