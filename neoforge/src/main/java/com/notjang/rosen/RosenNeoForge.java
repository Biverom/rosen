package com.notjang.rosen;


import com.notjang.rosen.platform.NeoForgeSoundRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@Mod(Constants.MOD_ID)
public class RosenNeoForge {

    public RosenNeoForge(IEventBus eventBus) {

        RosenCommon.init();

        NeoForge.EVENT_BUS.addListener(this::addReloadListener);
    }

    @SubscribeEvent
    private void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(CustomInstrumentLoader.INSTANCE);
    }

}