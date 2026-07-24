package com.opennight.manager;

import java.util.ArrayList;
import java.util.List;
import com.opennight.hud.TabListInfo;

public class HudManager {
    private final List<TabListInfo> hudElements = new ArrayList<>();

    public void register(TabListInfo element) {
        hudElements.add(element);
    }

    @SuppressWarnings("unchecked")
    public <T extends TabListInfo> T getHudElement(Class<T> clazz) {
        for (TabListInfo e : hudElements) {
            if (clazz.isInstance(e)) return (T) e;
        }
        return null;
    }

    public List<TabListInfo> getElements() {
        return hudElements;
    }
}
