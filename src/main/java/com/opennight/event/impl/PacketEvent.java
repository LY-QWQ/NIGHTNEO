package com.opennight.event.impl;

import com.opennight.event.Cancellable;
import com.opennight.event.Event;
import net.minecraft.network.protocol.Packet;

public class PacketEvent extends Event implements Cancellable {
    private boolean cancelled;
    private final Packet<?> packet;
    private final boolean incoming;

    public PacketEvent(Packet<?> p, boolean incoming) {
        this.packet = p;
        this.incoming = incoming;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public boolean isIncoming() {
        return incoming;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
