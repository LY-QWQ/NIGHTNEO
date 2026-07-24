package com.opennight.modules.impl.render;

import com.opennight.modules.Category;
import com.opennight.modules.Module;

public class ItemTags extends Module {
    public static ItemTags INSTANCE;

    public ItemTags() {
        super("ItemTags", Category.RENDER);
        INSTANCE = this;
    }
}
