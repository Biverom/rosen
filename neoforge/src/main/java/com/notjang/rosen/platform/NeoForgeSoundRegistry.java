package com.notjang.rosen.platform;

import com.notjang.rosen.Constants;
import com.notjang.rosen.platform.services.ISoundRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeSoundRegistry implements ISoundRegistry {

//    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
//            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);

//    @Override
//    public SoundEvent registerSound(String id) {
//        SOUND_EVENTS.register(id, SoundEvent::createVariableRangeEvent);
//        return null;
//    }

    @Override
    public SoundEvent registerSound(String id) {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }
}
