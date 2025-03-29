package com.notjang.rosen;


import com.notjang.rosen.platform.NeoForgeSoundRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class RosenNeoForge {

    public RosenNeoForge(IEventBus eventBus) {

        RosenCommon.init();

        //NeoForgeSoundRegistry.SOUND_EVENTS.register(eventBus);
    }
}