package com.example.examplemod.gui;

import net.minecraft.client.gui.GuiScreen;

public class ClickGUI extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


        FlatRender.smoothedRoundedRect(21, 21, 80, 200, 3, 0x111111CC);
        FlatRender.rect(21, 24, 80, 22, 0x11111166);
    }
}
