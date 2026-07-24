package com.opennight.modules.impl.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.opennight.gui.MaterialClickGui;
import com.opennight.gui.NewClickGui;
import com.opennight.gui.OldClickGui;
import com.opennight.gui.PanelClickGui;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.ModeSetting;

public class ClickGuiModule
extends Module {
    public static final Logger LOGGER = LogManager.getLogger(ClickGuiModule.class);
    public final ModeSetting styleSetting = new ModeSetting("Mode", "Old", "Panel", "New", "Material3").withDefault("Old");
    public final ModeSetting materialTheme = new ModeSetting("Material Theme", "Dark", "Light")
            .withVisibility(() -> this.styleSetting.is("Material3"));

    public ClickGuiModule() {
        super("ClickGui", Category.RENDER, 344);
    }

    @Override
    protected void onEnable() {
        try {
            if (this.styleSetting.is("Old")) {
                mc.setScreen(new OldClickGui());
            } else if (this.styleSetting.is("Panel")) {
                mc.setScreen(PanelClickGui.panelClickGui);
            } else if (this.styleSetting.is("Material3")) {
                mc.setScreen(MaterialClickGui.instance);
            } else {
                mc.setScreen(new NewClickGui());
            }
            LOGGER.info("ClickGUI opened successfully");
        } catch (Exception exception) {
            LOGGER.error("Error opening ClickGUI", exception);
        } finally {
            this.setEnabled(false);
        }
    }
}