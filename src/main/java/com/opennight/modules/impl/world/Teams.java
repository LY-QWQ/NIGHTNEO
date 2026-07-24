package com.opennight.modules.impl.world;

import com.opennight.modules.Category;
import com.opennight.modules.Module;
import com.opennight.settings.impl.ModeSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import java.util.Objects;

public class Teams extends Module {
    public static Teams INSTANCE;
    public static ModeSetting mode = new ModeSetting("Mode", "Color", "Scoreboard").withDefault("Scoreboard");
    private static final Minecraft mc = Minecraft.getInstance();

    public Teams() {
        super("Teams", Category.WORLD);
        INSTANCE = this;
    }

    public static boolean isSameTeam(Entity entity) {
        if (!INSTANCE.isEnabled()) return false;
        if (entity instanceof Player) {
            if (mode.is("Color")) {
                Integer n = entity.getTeamColor();
                Integer n2 = mc.player.getTeamColor();
                return n != null && n2 != null && n.equals(n2);
            }
            String s = getTeam(entity);
            String s2 = getTeam(mc.player);
            return Objects.equals(s, s2);
        }
        return false;
    }

    public static String getTeam(Entity entity) {
        PlayerInfo info = mc.getConnection().getPlayerInfo(entity.getUUID());
        if (info == null) return null;
        if (info.getTeam() != null) return info.getTeam().getName();
        return null;
    }
}
