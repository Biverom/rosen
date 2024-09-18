package com.notjang.rosen;

import com.notjang.rosen.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModSounds {
    private static final Map<ResourceLocation, Holder<SoundEvent>> SOUND_EVENTS = new LinkedHashMap<>();

    public static final Holder<SoundEvent> ACCORDION = add("block.note_block.accordion");
    public static final Holder<SoundEvent> BEACH = add("block.note_block.beach");
    public static final Holder<SoundEvent> CARILLON = add("block.note_block.carillon");
    public static final Holder<SoundEvent> CHIP = add("block.note_block.chip");
    public static final Holder<SoundEvent> CHOIR = add("block.note_block.choir");
    public static final Holder<SoundEvent> CLAP = add("block.note_block.clap");
    public static final Holder<SoundEvent> KICK = add("block.note_block.kick");
    public static final Holder<SoundEvent> LOG_DRUM = add("block.note_block.log_drum");
    public static final Holder<SoundEvent> ORGAN = add("block.note_block.organ");
    public static final Holder<SoundEvent> PIANO = add("block.note_block.piano");
    public static final Holder<SoundEvent> POWER_GUITAR = add("block.note_block.power_guitar");
    public static final Holder<SoundEvent> SAXOPHONE = add("block.note_block.saxophone");
    public static final Holder<SoundEvent> SITAR = add("block.note_block.sitar");
    public static final Holder<SoundEvent> SYNTH = add("block.note_block.synth");
    public static final Holder<SoundEvent> SYNTH_BASS = add("block.note_block.synth_bass");
    public static final Holder<SoundEvent> TIMPANI = add("block.note_block.timpani");
    public static final Holder<SoundEvent> VIBRAPHONE = add("block.note_block.vibraphone");
    public static final Holder<SoundEvent> VIOLIN = add("block.note_block.violin");

    private static Holder<SoundEvent> add(String id) {
        SoundEvent sound = Services.SOUND_REGISTRY.registerSound(id);
        Holder<SoundEvent> holder = new Holder.Direct<>(sound);
        SOUND_EVENTS.put(new ResourceLocation(Constants.MOD_ID, id), holder);
        return holder;
    }
}
