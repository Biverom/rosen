package com.notjang.rosen;

import net.fabricmc.api.ModInitializer;

public class RosenFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        RosenCommon.init();
    }
}
