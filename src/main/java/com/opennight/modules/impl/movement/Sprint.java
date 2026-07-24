package com.opennight.modules.impl.movement;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.SprintEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;

public class Sprint extends Module {
    public Sprint() { super("Sprint", Category.MOVEMENT); }

    @EventTarget
    public void onSprint(SprintEvent event) {
        event.setSprint(true);
    }
}
