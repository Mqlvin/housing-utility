package com.example.examplemod.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class EntityESP {


    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        for(Entity e : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            Minecraft.getMinecraft().theWorld.loadedEntityList.iterator();
            if(Minecraft.getMinecraft().thePlayer == e) continue;
            renderBox(e, event.partialTicks);
        }
    }

    private void renderBox(Entity entity, float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        float red = 0.75f;
        float green = 0.8f;
        float blue = 0.8f;
        float alpha = 0.3F;

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks) - mc.getRenderManager().viewerPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks) - mc.getRenderManager().viewerPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks) - mc.getRenderManager().viewerPosZ;

        GL11.glLineWidth(2f);

        drawShadedBox(new AxisAlignedBB(xPos - entity.width, yPos, zPos - entity.width,      xPos + entity.width, yPos + entity.height, zPos + entity.width), red, green, blue, 0.4F);
        drawOutlinedBoundingBox(new AxisAlignedBB(xPos - entity.width, yPos, zPos - entity.width,      xPos + entity.width, yPos + entity.height, zPos + entity.width), red, green, blue, 0.12F);

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawShadedBox(AxisAlignedBB aa, float r, float g, float b, float a) {
        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();
        wr.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        // dont go back to the end here - we will strip from the top, to the missing side, to the bottom
        ts.draw();


        wr.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();

        wr.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();

        wr.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();

        wr.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();

        ts.draw();
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa, float r, float g, float b, float a) {
        // System.out.println("outlined");
        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();

        // System.out.println(aa);

        wr.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        ts.draw();

        wr.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();
        ts.draw();

        GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.5F);

        wr.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();

        wr.pos(aa.maxX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.minZ).color(r, g, b, a).endVertex();

        wr.pos(aa.maxX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.maxX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();

        wr.pos(aa.minX, aa.minY, aa.maxZ).color(r, g, b, a).endVertex();
        wr.pos(aa.minX, aa.maxY, aa.maxZ).color(r, g, b, a).endVertex();

        wr.pos(aa.minX, aa.minY, aa.minZ).color(r, g, b, a).endVertex();
        ts.draw();

    }
}
