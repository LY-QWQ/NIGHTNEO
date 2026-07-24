package com.opennight.modules.impl.world;

import com.opennight.event.EventTarget;
import com.opennight.event.impl.PacketEvent;
import com.opennight.modules.Category;
import com.opennight.modules.Module;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;

public class AntiStaff extends Module {
    public AntiStaff() { super("AntiStaff", Category.WORLD); }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof ServerboundInteractPacket packet) {
            // Cancel attack packets on staff/vanished players
            // The actual check would need PlayerInfo but this is the framework
        }
    }
}
