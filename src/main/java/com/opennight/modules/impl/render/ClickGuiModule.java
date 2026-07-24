package com.opennight.modules.impl.render;

import com.opennight.modules.Category;
import com.opennight.modules.Module;
import net.minecraft.client.Minecraft;

public class ClickGuiModule extends Module {
    public static ClickGuiModule INSTANCE;
    public boolean isOpen = false;

    public ClickGuiModule() {
        super("ClickGui", Category.RENDER, org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT);
        INSTANCE = this;
    }

    @Override
    protected void onEnable() {
        Minecraft.getInstance().setScreen(new com.opennight.gui.NewClickGui());
        this.setEnabled(false);
        isOpen = true;
    }

    @Override
    protected void onDisable() {
        isOpen = false;
    }
}
