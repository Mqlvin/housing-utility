package com.example.examplemod.module;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class GhostBlock {
    public static long lastBlockPlaced = -1;
    public static ArrayList<BlockPos> blocks = new ArrayList<>();

    public static void removeBlock(MovingObjectPosition object) {
        if (object != null && object.getBlockPos() != null) {
            Block lookingAtblock = Minecraft.getMinecraft().theWorld.getBlockState(object.getBlockPos()).getBlock();
            if (!isInteractable(lookingAtblock) && lookingAtblock != Blocks.air) {
                Minecraft.getMinecraft().theWorld.setBlockToAir(object.getBlockPos());
            }
        }
    }

    public static boolean isInteractable(Block block) {
        return (new ArrayList<>(Arrays.asList((Object[]) new Block[] { (Block) Blocks.chest, Blocks.lever, Blocks.trapped_chest, Blocks.wooden_button, Blocks.stone_button, (Block)Blocks.skull }))).contains(block);
    }

    public static void addBlock() {
        if(lastBlockPlaced + 200 < System.currentTimeMillis()) {
            BlockPos pos = Minecraft.getMinecraft().objectMouseOver.getBlockPos().offset(Minecraft.getMinecraft().objectMouseOver.sideHit);
            BlockPos playerPos = new BlockPos(Math.floor(Minecraft.getMinecraft().thePlayer.posX), Math.floor(Minecraft.getMinecraft().thePlayer.posY), Math.floor(Minecraft.getMinecraft().thePlayer.posZ));

            System.out.println(pos);
            System.out.println(playerPos);

            if(playerPos.equals(pos) || playerPos.add(0, 1, 0).equals(pos)) {
                return;
            }
            blocks.add(pos);
            updateAllBlocks();

            lastBlockPlaced = System.currentTimeMillis();
        }
    }

    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        System.out.println(event.button);
        if(event.button == 1 && !event.buttonstate) {
            System.out.println("hello");
            lastBlockPlaced = -1;
        }
    }

    public static void updateAllBlocks() {
        blocks.forEach(pos -> {
            Minecraft.getMinecraft().theWorld.setBlockState(pos, Blocks.stained_hardened_clay.getDefaultState(), 3);
        });
    }
}
