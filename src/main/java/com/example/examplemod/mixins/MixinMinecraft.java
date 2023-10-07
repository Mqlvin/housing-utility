package com.example.examplemod.mixins;

import com.example.examplemod.HousingUtils;
import com.example.examplemod.module.GhostBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {



    @Shadow
    private int leftClickCounter;
    @Shadow
    private MovingObjectPosition objectMouseOver;
    @Shadow
    private EffectRenderer effectRenderer;
    @Shadow
    private PlayerControllerMP playerController;
    @Shadow
    private WorldClient theWorld;
    @Shadow
    private int rightClickDelayTimer;



    @Inject(method = "dispatchKeypresses", at = @At("HEAD"))
    private void onKeypress(CallbackInfo ci) {
        int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() : Keyboard.getEventKey();

        if(Minecraft.getMinecraft().currentScreen == null) HousingUtils.inputHandler.handleKey(i, Keyboard.getEventKeyState());
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void sendClickBlockToController(boolean leftClick)
    {
        if (!leftClick)
        {
            this.leftClickCounter = 0;
        }

        if (this.leftClickCounter <= 0 && !Minecraft.getMinecraft().thePlayer.isUsingItem())
        {
            if (leftClick && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = this.objectMouseOver.getBlockPos();

                if(HousingUtils.inputHandler.isGDown) {
                    MovingObjectPosition object = Minecraft.getMinecraft().thePlayer.rayTrace(Minecraft.getMinecraft().playerController.getBlockReachDistance(), 1.0F);
                    GhostBlock.removeBlock(object);

                    return;
                }

                if (this.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air && this.playerController.onPlayerDamageBlock(blockpos, this.objectMouseOver.sideHit))
                {
                    this.effectRenderer.addBlockHitEffects(blockpos, this.objectMouseOver);
                    Minecraft.getMinecraft().thePlayer.swingItem();
                }
            }
            else
            {
                this.playerController.resetBlockRemoving();
            }
        }
    }

    @Inject(method = "clickMouse", at = @At("HEAD"), cancellable = true)
    public void onClickMouse(CallbackInfo ci) {
        if(HousingUtils.inputHandler.isGDown) ci.cancel();
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"), cancellable = true)
    public void onRightClickMouse(CallbackInfo ci) {
        if(!HousingUtils.inputHandler.isGDown) return;

        GhostBlock.addBlock();
        ci.cancel();
    }

    @Inject(method = "runTick", at = @At("RETURN"))
    public void postTick(CallbackInfo ci) {
        if(rightClickDelayTimer == 6) GhostBlock.lastBlockPlaced = 0;
    }
}
