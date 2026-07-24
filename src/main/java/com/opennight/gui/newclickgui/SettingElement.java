package com.opennight.gui.newclickgui;

import lombok.Getter;
import lombok.Generated;
import com.opennight.gui.newclickgui.CategoryPanel;
import com.opennight.gui.newclickgui.UIElement;
import com.opennight.settings.Setting;
import com.opennight.utils.animation.SmoothAnimationTimer;

public abstract class SettingElement<T extends Setting<?>>
extends UIElement {
    @Getter
    protected final CategoryPanel parentPanel;
    @Getter
    protected final T setting;
    @Getter
    protected final SmoothAnimationTimer visibilityTimer = new SmoothAnimationTimer();

    @Generated
    public SettingElement(CategoryPanel categoryPanel, T setting) {
        this.parentPanel = categoryPanel;
        this.setting = setting;
    }
}