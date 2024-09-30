package com.notjang.rosen.platform.services;

import net.minecraft.sounds.SoundEvent;

public interface ISoundRegistry {
   SoundEvent registerSound(String id);
}