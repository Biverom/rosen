package com.notjang.rosen.mixin;

import com.notjang.rosen.ModSounds;
import com.notjang.rosen.InstrumentContainer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Debug(export = true)
@Mixin(NoteBlockInstrument.class)
@Unique
public class NoteblockInstrumentMixin {

    @Shadow
    @Final
    @Mutable
    private static NoteBlockInstrument[] $VALUES;

    static {
        rosen$initInstruments();
    }

    @Unique
    private static void rosen$addInstrument(String internalName, String name, Holder<SoundEvent> sound) {
        ArrayList<NoteBlockInstrument> variants = new ArrayList<>(Arrays.asList($VALUES));
        NoteBlockInstrument instrument = invokeInit(internalName, variants.get(variants.size() - 1).ordinal() + 1, name, sound, NoteBlockInstrument.Type.BASE_BLOCK);
        variants.add(instrument);
        $VALUES = variants.toArray(new NoteBlockInstrument[0]);
        InstrumentContainer.instruments.put(name, instrument);
    }

    @Unique
    private static void rosen$initInstruments() {
        rosen$addInstrument("ACCORDION", "accordion", ModSounds.ACCORDION);
        rosen$addInstrument("BEACH", "beach", ModSounds.BEACH);
        rosen$addInstrument("CARILLON", "carillon", ModSounds.CARILLON);
        rosen$addInstrument("MUSIC_BOX", "music_box", ModSounds.MUSIC_BOX);
        rosen$addInstrument("CHOIR", "choir", ModSounds.CHOIR);
        rosen$addInstrument("DHOLAK", "dholak", ModSounds.DHOLAK);
        rosen$addInstrument("KICK", "kick", ModSounds.KICK);
        rosen$addInstrument("LOG_DRUM", "log_drum", ModSounds.LOG_DRUM);
        rosen$addInstrument("ORGAN", "organ", ModSounds.ORGAN);
        rosen$addInstrument("PIANO", "piano", ModSounds.PIANO);
        rosen$addInstrument("POWER_GUITAR", "power_guitar", ModSounds.POWER_GUITAR);
        rosen$addInstrument("SAXOPHONE", "saxophone", ModSounds.SAXOPHONE);
        rosen$addInstrument("SITAR", "sitar", ModSounds.SITAR);
        rosen$addInstrument("SYNTH", "synth", ModSounds.SYNTH);
        rosen$addInstrument("SYNTH_BASS", "synth_bass", ModSounds.SYNTH_BASS);
        rosen$addInstrument("TIMPANI", "timpani", ModSounds.TIMPANI);
        rosen$addInstrument("VIBRAPHONE", "vibraphone", ModSounds.VIBRAPHONE);
        rosen$addInstrument("VIOLIN", "violin", ModSounds.VIOLIN);
    }

    @Invoker("<init>")
    public static NoteBlockInstrument invokeInit(String internalName, int sequence, String name, Holder<SoundEvent> sound, NoteBlockInstrument.Type type) {
        throw new AssertionError();
    }
}
