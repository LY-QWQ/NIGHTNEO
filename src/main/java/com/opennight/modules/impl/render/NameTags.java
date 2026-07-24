package com.opennight.modules.impl.render;

import com.opennight.event.impl.PacketEvent;
import com.opennight.event.impl.Render2DEvent;
import com.opennight.event.impl.RenderEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.modules.impl.render.nametag.NameTagStyle;
import com.opennight.settings.impl.BooleanSetting;
import com.opennight.settings.impl.ModeSetting;
import com.opennight.settings.impl.NumberSetting;
import com.opennight.event.EventTarget;

public class NameTags
extends Module {
    public static NameTags INSTANCE;
    public final ModeSetting styleSetting = new ModeSetting("Style", "Opal", "Simple").withDefault("Opal");
    public final NumberSetting scaleSetting = new NumberSetting("Scale", 0.4, 0.1, 1.0, 0.01);
    public final NumberSetting distanceSetting = new NumberSetting("Max Distance", 128.0, 8.0, 256.0, 1.0);
    public final BooleanSetting showHealthSetting = new BooleanSetting("Invisibles", true);
    public final BooleanSetting showArmorSetting = new BooleanSetting("Show Artifacts", true);
    public final BooleanSetting showPingSetting = new BooleanSetting("Hide Teammates", false);

    public NameTags() {
        super("NameTags", Category.RENDER);
        INSTANCE = this;
        NameTagStyle.registerStyles();
    }

    @Override
    public void onEnable() {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onEnable();
        }
    }

    @Override
    public void onDisable() {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onDisable();
        }
    }

    @EventTarget
    public void onRender(RenderEvent renderEvent) {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onRender(renderEvent);
        }
    }

    @EventTarget
    public void onRender2D(Render2DEvent render2DEvent) {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onRender2D(render2DEvent);
        }
    }

    @EventTarget
    public void onPacket(PacketEvent packetEvent) {
        NameTagStyle nameTagStyle = NameTagStyle.getByName(this.styleSetting.getValue());
        if (nameTagStyle != null) {
            nameTagStyle.onPacket(packetEvent);
        }
    }
}