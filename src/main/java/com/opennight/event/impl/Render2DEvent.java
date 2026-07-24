package com.opennight.event.impl;

import com.opennight.event.Event;
import net.minecraft.client.gui.GuiGraphics;

public class Render2DEvent extends Event {
    public final GuiGraphics graphics;
    public final float partialTick;

    public Render2DEvent(GuiGraphics g, float pt) {
        this.graphics = g;
        this.partialTick = pt;
    }
}
