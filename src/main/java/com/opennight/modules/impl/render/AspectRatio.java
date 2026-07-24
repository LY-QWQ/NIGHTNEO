package com.opennight.modules.impl.render;

import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.NumberSetting;

public class AspectRatio
extends Module {
    public static AspectRatio INSTANCE;
    public final NumberSetting ratioSetting = new NumberSetting("Ratio", 1.78f, 0.1f, 5.0f, 0.1f);

    public AspectRatio() {
        super("AspectRatio", Category.RENDER);
        INSTANCE = this;
    }
}