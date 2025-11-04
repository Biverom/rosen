package com.notjang.rosen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CustomInstrumentLoader extends SimpleJsonResourceReloadListener {

    public static final HashMap<String, SoundEvent> customInstruments = new HashMap<>();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static final CustomInstrumentLoader INSTANCE = new CustomInstrumentLoader();

    public CustomInstrumentLoader() {
        super(GSON, "noteblock_instrument");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> jsons, @NotNull ResourceManager manager, @NotNull ProfilerFiller profiler) {
        customInstruments.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement element = entry.getValue();

            try {
                JsonObject json = element.getAsJsonObject();
                String name = GsonHelper.getAsString(json, "name");
                String soundEventId = GsonHelper.getAsString(json, "sound_event");
                ResourceLocation soundLocation = ResourceLocation.parse(soundEventId);
                SoundEvent soundEvent = BuiltInRegistries.SOUND_EVENT.get(soundLocation);
                if (soundEvent == null) {
                    soundEvent = SoundEvent.createVariableRangeEvent(soundLocation);
                }
                customInstruments.put(name, soundEvent);
                Constants.LOG.info("Loaded custom noteblock instrument '{}' -> {}", name, soundEventId);
            } catch (Exception e) {
                Constants.LOG.error("Failed to load custom noteblock instrument from {}: {}", id, e.toString());
            }
        }

        if (!customInstruments.isEmpty())
            Constants.LOG.info("Loaded {} custom noteblock instruments", customInstruments.size());
    }
}
