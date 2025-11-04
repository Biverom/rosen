package com.notjang.rosen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.notjang.rosen.Constants;
import com.notjang.rosen.CustomInstrumentLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import oshi.util.tuples.Pair;

@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    @ModifyArg(method = "triggerEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSeededSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V"), index = 4)
    private Holder<SoundEvent> triggerEvent(Holder<SoundEvent> sound, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) Level level, @Local(argsOnly = true) BlockPos pos) {
        if (state.getValue(NoteBlock.INSTRUMENT).getSerializedName().equals("custom")) {
            for (var ci : CustomInstrumentLoader.customInstruments.entrySet()) {
                TagKey<Block> tag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, ci.getKey() + "_noteblocks"));
                BlockState instrumentBlock = level.getBlockState(pos.below());
                if (instrumentBlock.is(tag)) {
                    sound = new Holder.Direct<>(ci.getValue());
                    break;
                }
            }
        }
        return sound;
    }
}
