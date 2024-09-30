package com.notjang.rosen;

import net.fabricmc.api.ModInitializer;

public class Rosen implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello Fabric world!");
        RosenCommon.init();
    }
}
