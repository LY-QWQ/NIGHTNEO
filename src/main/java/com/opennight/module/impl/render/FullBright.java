package com.opennight.module.impl.render;

import com.opennight.module.Category;
import com.opennight.module.Module;
import net.minecraft.client.Minecraft;

public class FullBright extends Module {
    private double oldGamma;

    public FullBright() {
        super("FullBright", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        Minecraft mc = Minecraft.getInstance();
        oldGamma = mc.options.gamma().get();
        mc.options.gamma().set(100.0);
    }

    @Override
    protected void onDisable() {
        Minecraft.getInstance().options.gamma().set(oldGamma);
    }
}
