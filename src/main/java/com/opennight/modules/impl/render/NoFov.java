package com.opennight.modules.impl.render;

import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.ModeSetting;
import com.opennight.settings.impl.NumberSetting;

public class NoFov extends Module {
    public static NoFov INSTANCE;

    public final ModeSetting modeSetting = new ModeSetting("Mode", "Constant", "Custom").withDefault("Constant");
    public final NumberSetting fovSetting = new NumberSetting("FOV", 90, 1, 179, 1);
    public final NumberSetting multiplierSetting = new NumberSetting("Multiplier", 1.0, 0.1, 1.5, 0.05);

    public NoFov() {
        super("NoFov", Category.RENDER);
        INSTANCE = this;
    }

    public boolean isConstant() {
        return this.modeSetting.is("Constant");
    }
}
