package com.example.examplemod.module.render;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ChestESP {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        for(TileEntity e : Minecraft.getMinecraft().theWorld.loadedTileEntityList) {
            BlockPos tilePos = e.getPos();
            AxisAlignedBB bb = new AxisAlignedBB(tilePos.getX() + 1, tilePos.getY() + 1, tilePos.getZ() + 1, tilePos.getX(), tilePos.getY(), tilePos.getZ());

            if(e instanceof TileEntityChest) {
                boolean trapped = ((TileEntityChest) e).getChestType() == 1;

                float red = trapped ? 0.9f : 0.3f;
                float green = trapped ? 0.3f : 0.9f;
                float blue = 0.3f;
                float alpha = 1.0F;
                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(770, 771);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_LINE_SMOOTH);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                GL11.glColor4f(red, green, blue, alpha);

                drawShadedBox(bb.offset(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ), red, green, blue, 0.5f);

                // System.out.println(bb);

                GL11.glDisable(GL11.GL_LINE_SMOOTH);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            } else if(e instanceof TileEntityEnderChest) {
                float red = 0.1f;
                float green = 0.1f;
                float blue = 1.0f;
                float alpha = 1.0F;
                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(770, 771);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_LINE_SMOOTH);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                GL11.glColor4f(red, green, blue, alpha);

                drawShadedBox(bb.offset(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ), red, green, blue, 0.5f);

                // System.out.println(bb);

                GL11.glDisable(GL11.GL_LINE_SMOOTH);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }
        }
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
}
