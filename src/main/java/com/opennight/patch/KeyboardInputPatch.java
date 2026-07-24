package com.opennight.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import com.opennight.NightNeo;
import com.opennight.event.impl.StrafeEvent;

@Patch(KeyboardInput.class)
public class KeyboardInputPatch extends Input {
    @Inject(method = "tick", desc = "(ZF)V", at = @At(At.Type.TAIL))
    public static void onTick(KeyboardInput input, boolean isSneaking, float sneakMultiplier, CallbackInfo callbackInfo) {
        input.forwardImpulse = input.up == input.down ? 0.0f : (input.up ? 1.0f : -1.0f);
        input.leftImpulse = input.left == input.right ? 0.0f : (input.left ? 1.0f : -1.0f);
        StrafeEvent event = new StrafeEvent(input.forwardImpulse, input.leftImpulse, input.jumping);
        if (NightNeo.isReady()) {
            NightNeo.instance.getEventBus().call(event);
        }
        double sneakFactor = 0.3;
        input.forwardImpulse = event.getForward();
        input.leftImpulse = event.getStrafe();
        input.jumping = event.isSprinting();
        if (isSneaking) {
            input.leftImpulse = (float) (input.leftImpulse * sneakFactor);
            input.forwardImpulse = (float) (input.forwardImpulse * sneakFactor);
        }
    }
}
