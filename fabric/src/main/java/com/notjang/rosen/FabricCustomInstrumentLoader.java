package com.notjang.rosen;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;

public class FabricCustomInstrumentLoader extends CustomInstrumentLoader implements IdentifiableResourceReloadListener {

    public static final FabricCustomInstrumentLoader FABRIC_INSTANCE = new FabricCustomInstrumentLoader();

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "noteblock_instruments");
    }
}
