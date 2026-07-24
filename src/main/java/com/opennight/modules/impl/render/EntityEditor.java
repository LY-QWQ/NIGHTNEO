package com.opennight.modules.impl.render;

import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.BooleanSetting;
import com.opennight.settings.impl.ModeSetting;
import com.opennight.utils.render.EntityUtil;

public class EntityEditor extends Module {

    public static EntityEditor INSTANCE;

    private final ModeSetting targetSetting = new ModeSetting("Target", "Pig", "Cow", "Zombie", "Creeper").withDefault("Pig");
    private final BooleanSetting ignoreSelf = new BooleanSetting("Ignore Self", true);
    private final BooleanSetting betterItemView = new BooleanSetting("Better Item View", true,() -> this.allEntity.getValue());
    private final BooleanSetting allEntity = new BooleanSetting("AllEntity", false);
    private final BooleanSetting excludeDrops = new BooleanSetting("Exclude Drops", true, () -> this.allEntity.getValue());

    public EntityEditor() {
        super("EntityEditor", Category.RENDER);
        INSTANCE = this;
    }

    public String getTargetEntity() { return targetSetting.getValue(); }
    public boolean getIgnoreSelf() { return ignoreSelf.getValue(); }
    public boolean getAllEntity() { return allEntity.getValue(); }
    public boolean getBetterItemView() {return betterItemView.getValue(); }
    public boolean getExcludeDrops() { return excludeDrops.getValue(); }

    @Override
    protected void onDisable() {
        super.onDisable();
        EntityUtil.clearCache();
    }
}
