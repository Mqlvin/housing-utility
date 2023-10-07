package com.example.examplemod.gui;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class FlatRender {
    private static void roundedRectPriv(float x, float y, float x1, float y1, float radius, int colour) {
        x *= 2;
        y *= 2;
        x1 *= 2;
        y1 *= 2;
        radius *= 2;

        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();

        GL11.glPushMatrix();

        float r = (colour >> 24 & 0xFF) / 255.0F;
        float g = (colour >> 16 & 0xFF) / 255.0F;
        float b = (colour >> 8 & 0xFF) / 255.0F;
        float a = (colour & 0xFF) / 255.0F;

        GL11.glColor4f(r, g, b, a);

        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1f);

        wr.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
        int i;
        for (i = 0; i <= 90; i += 3)
            wr.pos(x + radius + Math.sin(Math.toRadians(i)) * radius * -1, y + radius + Math.cos(Math.toRadians(i)) * radius * -1, 0.0D).endVertex();
        for (i = 90; i <= 180; i += 3)
            wr.pos(x + radius + Math.sin(Math.toRadians(i)) * radius * -1, y1 - radius + Math.cos(Math.toRadians(i)) * radius * -1, 0.0D).endVertex();
        for (i = 0; i <= 90; i += 3)
            wr.pos(x1 - radius + Math.sin(Math.toRadians(i)) * radius, y1 - radius + Math.cos(Math.toRadians(i)) * radius, 0.0D).endVertex();
        for (i = 90; i <= 180; i += 3)
            wr.pos(x1 - radius + Math.sin(Math.toRadians(i)) * radius, y + radius + Math.cos(Math.toRadians(i)) * radius, 0.0D).endVertex();

        ts.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glScalef(2f, 2f, 2f);

        GL11.glPopMatrix();
    }

    public static void rect(float x, float y, float width, float height, int colour) {
        x *= 2;
        y *= 2;
        float x1 = x + (width * 2);
        float y1 = y + (height * 2);

        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();

        GL11.glPushMatrix();

        float r = (colour >> 24 & 0xFF) / 255.0F;
        float g = (colour >> 16 & 0xFF) / 255.0F;
        float b = (colour >> 8 & 0xFF) / 255.0F;
        float a = (colour & 0xFF) / 255.0F;

        GL11.glColor4f(r, g, b, a);

        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1f);

        wr.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
        wr.pos(x, y, 0.0D).endVertex();
        wr.pos(x, y1, 0.0D).endVertex();
        wr.pos(x1, y1, 0.0D).endVertex();
        wr.pos(x1, y, 0.0D).endVertex();

        ts.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glScalef(2f, 2f, 2f);

        GL11.glPopMatrix();
    }

    private static void privRoundedRectBorder(float x, float y, float x1, float y1, float radius, int colour) {
        x *= 2;
        y *= 2;
        x1 *= 2;
        y1 *= 2;
        radius *= 2;

        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();

        GL11.glPushMatrix();

        float r = (colour >> 24 & 0xFF) / 255.0F;
        float g = (colour >> 16 & 0xFF) / 255.0F;
        float b = (colour >> 8 & 0xFF) / 255.0F;
        float a = (colour & 0xFF) / 255.0F;

        GL11.glColor4f(r, g, b, a);




        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1f);

        wr.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION);
        int i;
        for (i = 0; i <= 90; i += 3)
            wr.pos(x + radius + Math.sin(Math.toRadians(i)) * radius * -1, y + radius + Math.cos(Math.toRadians(i)) * radius * -1, 0.0D).endVertex();
        for (i = 90; i <= 180; i += 3)
            wr.pos(x + radius + Math.sin(Math.toRadians(i)) * radius * -1, y1 - radius + Math.cos(Math.toRadians(i)) * radius * -1, 0.0D).endVertex();
        for (i = 0; i <= 90; i += 3)
            wr.pos(x1 - radius + Math.sin(Math.toRadians(i)) * radius, y1 - radius + Math.cos(Math.toRadians(i)) * radius, 0.0D).endVertex();
        for (i = 90; i <= 180; i += 3)
            wr.pos(x1 - radius + Math.sin(Math.toRadians(i)) * radius, y + radius + Math.cos(Math.toRadians(i)) * radius, 0.0D).endVertex();

        ts.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glLineWidth(1f);
        GL11.glScalef(2f, 2f, 2f);

        GL11.glPopMatrix();
    }

    public static void smoothedRoundedRect(int x, int y, int width, int height, int radius, int backgroundColour) {
        roundedRectPriv(x, y, x + width, y + height, radius, backgroundColour);
        privRoundedRectBorder(x, y, x + width, y + height, radius, backgroundColour);
    }

    public static void roundedRect(int x, int y, int width, int height, int radius, int backgroundColour) {
        roundedRectPriv(x, y, x + width, y + height, radius, backgroundColour);
    }

    public static void smoothedRoundedRect(int x, int y, int width, int height, int radius, int backgroundColour, int lineColour) {
        roundedRectPriv(x, y, x + width, y + height, radius, backgroundColour);
        privRoundedRectBorder(x, y, x + width, y + height, radius, lineColour);
    }
}
