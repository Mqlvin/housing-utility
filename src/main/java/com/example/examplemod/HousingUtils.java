package com.example.examplemod;

import com.example.examplemod.api.InputHandler;
import com.example.examplemod.module.GhostBlock;
import com.example.examplemod.module.render.BarrierVisibility;
import com.example.examplemod.module.render.ChestESP;
import com.example.examplemod.module.render.EntityESP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = HousingUtils.MODID, version = HousingUtils.VERSION)
public class HousingUtils {
    public static final String MODID = "aaaa";
    public static final String VERSION = "1.0";

    public static final InputHandler inputHandler = new InputHandler();

    public static final EntityESP entityEsp = new EntityESP();
    public static final ChestESP chestEsp = new ChestESP();
    public static final BarrierVisibility barrierVis = new BarrierVisibility();
    public static final GhostBlock ghostBlock = new GhostBlock();


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
