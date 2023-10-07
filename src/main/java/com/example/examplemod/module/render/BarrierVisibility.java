package com.example.examplemod.module.render;

import net.minecraft.client.Minecraft;

public class BarrierVisibility {
    public boolean enabled = false;

    public void toggle() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        enabled = !enabled;
    }
}
