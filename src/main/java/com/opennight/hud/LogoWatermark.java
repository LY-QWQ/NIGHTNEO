package com.opennight.hud;

import com.opennight.ClientBase;
import com.opennight.NightNeo;
import com.opennight.event.impl.GlRenderEvent;
import com.opennight.render.DrawContext;
import com.opennight.render.FontPresets;
import com.opennight.render.FontRenderer;

public class LogoWatermark {
    private static final String LOGO = "N";
    private static final FontRenderer LOGO_FONT = FontPresets.nightIcon(96.0f);

    public void onGlRender(GlRenderEvent event) {
        if (ClientBase.mc.player == null || NightNeo.instance.getHudManager() == null) {
            return;
        }
        ModuleListHud moduleList = NightNeo.instance.getHudManager().getHudElement(ModuleListHud.class);
        int topColor = moduleList == null ? 0xFFFFFFFF : moduleList.getThemeColor(0, 0.0f, 1);
        int bottomColor = moduleList == null ? 0xFFFFFFFF : moduleList.getThemeColor(1, 1.0f, 1);
        DrawContext drawContext = event.drawContext();
        drawContext.drawStringGradient(LOGO, -2.0f, 18.0f + LOGO_FONT.getMetrics().capHeight(), LOGO_FONT, topColor, bottomColor);
    }
}
