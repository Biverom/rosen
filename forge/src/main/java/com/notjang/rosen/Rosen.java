package com.notjang.rosen;

import net.minecraft.tags.TagKey;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class Rosen {
    
    public Rosen() {
        Constants.LOG.info("Hello Forge world!");
        RosenCommon.init();
    }
}