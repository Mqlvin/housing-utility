package com.example.examplemod.api;

import com.example.examplemod.HousingUtils;
import com.example.examplemod.gui.ClickGUI;
import com.example.examplemod.module.GhostBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

public class InputHandler {
    public boolean isGDown = false;
    public boolean entityEspEnabled = false;
    public boolean chestEspEnabled = false;

    public void handleKey(int key, boolean down) {
        if(key == Keyboard.KEY_G) {
            isGDown = down;
            GhostBlock.updateAllBlocks();
        }

        if(key == Keyboard.KEY_N && down) {
            if(entityEspEnabled) {
                MinecraftForge.EVENT_BUS.unregister(HousingUtils.entityEsp);
            } else {
                MinecraftForge.EVENT_BUS.register(HousingUtils.entityEsp);
            }

            entityEspEnabled = !entityEspEnabled;
        }

        if(key == Keyboard.KEY_M && down) {
            if(chestEspEnabled) {
                MinecraftForge.EVENT_BUS.unregister(HousingUtils.chestEsp);
            } else {
                MinecraftForge.EVENT_BUS.register(HousingUtils.chestEsp);
            }

            chestEspEnabled = !chestEspEnabled;
        }

        if(key == Keyboard.KEY_B && down) {
            HousingUtils.barrierVis.toggle();
        }
    }

    public void handleMouse(int key, boolean down) {

    }
}
