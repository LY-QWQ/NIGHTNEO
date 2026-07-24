package com.opennight.manager;

import net.minecraft.world.entity.player.Player;

public class TargetManager {
    private Player target;
    private double distance;

    public Player getTarget() { return target; }
    public void setTarget(Player target) { this.target = target; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public void reset() {
        this.target = null;
        this.distance = Double.MAX_VALUE;
    }
}
