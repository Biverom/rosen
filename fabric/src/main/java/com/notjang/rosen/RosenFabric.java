package com.notjang.rosen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public class RosenFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RosenCommon.init();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(FabricCustomInstrumentLoader.FABRIC_INSTANCE);
    }
}
