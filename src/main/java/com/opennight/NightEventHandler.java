package com.opennight;

import com.opennight.event.impl.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.NeoForge;

public class NightEventHandler {
    public NightEventHandler() {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post e) {
        NightNeo.eventBus.call(new TickEvent(TickEvent.Phase.POST));
    }

    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post e) {
        NightNeo.eventBus.call(new Render2DEvent(e.getGuiGraphics(), e.getPartialTick()));
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key e) {
        NightNeo.eventBus.call(new KeyEvent(e.getKey(), e.getAction(), e.getModifiers()));
    }
}
