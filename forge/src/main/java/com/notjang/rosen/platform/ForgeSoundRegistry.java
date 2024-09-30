package com.notjang.rosen.platform;

import com.notjang.rosen.Constants;
import com.notjang.rosen.platform.services.ISoundRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ForgeSoundRegistry implements ISoundRegistry {

    @Override
    public SoundEvent registerSound(String id) {
        ResourceLocation identifier = new ResourceLocation(Constants.MOD_ID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }
}