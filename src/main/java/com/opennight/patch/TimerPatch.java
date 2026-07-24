package com.opennight.patch;

// TODO: Timer class removed in 1.21, needs new approach.
// The net.minecraft.client.Timer class and its fields (tickDelta, partialTick,
// lastMs, msPerTick) no longer exist. In 1.21.8 tick rate is managed by
// ClientTickRateManager. The advanceTime method must be re-implemented using
// the new timing API.
//
// Original patch targeted net.minecraft.client.Timer.advanceTime(long).
// import asm.patchify.annotation.Overwrite;
// import asm.patchify.annotation.Patch;
// import net.minecraft.client.Timer;
// import com.opennight.NightNeo;
// import com.opennight.utils.misc.ReflectionUtil;
//
// @Patch(Timer.class)
public class TimerPatch {
    // @Overwrite(method = "advanceTime", desc = "(J)I")
    // public static int overwriteAdvanceTime(Timer timer, long currentMs) {
    //     long lastMs = (Long) ReflectionUtil.getStaticField(timer, "lastMs", "net/minecraft/client/Timer");
    //     float msPerTick = (Float) ReflectionUtil.getStaticField(timer, "msPerTick", "net/minecraft/client/Timer");
    //     if (NightNeo.serverTickRate == 1.0f) {
    //         timer.tickDelta = (float) (currentMs - lastMs) / msPerTick;
    //     } else {
    //         timer.tickDelta = (float) (currentMs - lastMs) / msPerTick * NightNeo.serverTickRate;
    //     }
    //     ReflectionUtil.setInstanceField(timer, currentMs, "lastMs", "net/minecraft/client/Timer");
    //     timer.partialTick = timer.partialTick + timer.tickDelta;
    //     int wholeTicks = (int) timer.partialTick;
    //     timer.partialTick -= wholeTicks;
    //     return wholeTicks;
    // }
}
