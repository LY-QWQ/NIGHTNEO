package com.opennight.modules.impl.combat.antikb;

import java.util.HashMap;
import java.util.Optional;
import com.opennight.ClientBase;
import com.opennight.event.impl.DisconnectEvent;
import com.opennight.event.impl.GameTickEvent;
import com.opennight.event.impl.MotionEvent;
import com.opennight.event.impl.PreMotionEvent;
import com.opennight.event.impl.ReceivePacketEvent;
import com.opennight.event.impl.Render2DEvent;
import com.opennight.event.impl.RenderEvent;
import com.opennight.event.impl.RotationEvent;
import com.opennight.event.impl.SprintEvent;
import com.opennight.event.impl.StrafeEvent;
import com.opennight.event.impl.TickEvent;
import com.opennight.modules.impl.combat.antikb.JumpResetMode;
import com.opennight.modules.impl.combat.antikb.MixMode;
import com.opennight.modules.impl.combat.antikb.NoXZMode;

public abstract class AntiKBMode
extends ClientBase {
    protected final String name;
    private static final HashMap<Class<? extends AntiKBMode>, AntiKBMode> modes = new HashMap<>();

    public AntiKBMode(String string) {
        this.name = string;
    }

    public static void initModes() {
        modes.put(JumpResetMode.class, new JumpResetMode());
        modes.put(MixMode.class, new MixMode());
        modes.put(NoXZMode.class, new NoXZMode());
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract String getName();

    public static Optional<AntiKBMode> findMode(String string) {
        return modes.values().stream().filter(antiKBMode -> antiKBMode.name.equals(string)).findFirst();
    }

    public abstract void onRotation(RotationEvent var1);

    public abstract void onReceivePacket(ReceivePacketEvent var1);

    public abstract void onDisconnect(DisconnectEvent var1);

    public abstract void onPreMotion(PreMotionEvent var1);

    public abstract void onGameTick(GameTickEvent var1);

    public abstract void onSprint(SprintEvent var1);

    public abstract void onTick(TickEvent var1);

    public abstract void onStrafe(StrafeEvent var1);

    public abstract void onMotion(MotionEvent var1);

    public void onRender(RenderEvent renderEvent) {
    }

    public void onRender2D(Render2DEvent render2DEvent) {
    }

    public boolean isActive() {
        return false;
    }
}