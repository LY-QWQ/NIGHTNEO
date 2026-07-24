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
        NightNeo.eventBus.call(new TickEvent());
        NightNeo.getInstance().onTick();
    }

    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post e) {
        float pt = e.getPartialTick().getGameTimeDeltaTicks();
        NightNeo.eventBus.call(new Render2DEvent(new com.mojang.blaze3d.vertex.PoseStack(), e.getGuiGraphics(), pt));
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key e) {
        NightNeo.eventBus.call(new KeyEvent(e.getKey(), e.getAction() != 0));
    }
}
