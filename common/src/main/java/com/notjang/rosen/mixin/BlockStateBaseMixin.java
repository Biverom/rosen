package com.notjang.rosen.mixin;

import com.notjang.rosen.Constants;
import com.notjang.rosen.CustomInstrumentLoader;
import com.notjang.rosen.InstrumentContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin {

    @Inject(method = "instrument()Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;", at = @At("RETURN"), cancellable = true)
    private void instrument(CallbackInfoReturnable<NoteBlockInstrument> cir) {
        NoteBlockInstrument currentInstrument = cir.getReturnValue();

        NoteBlockInstrument custom = null;
        for (NoteBlockInstrument instrument : NoteBlockInstrument.values()) {
            String name = instrument.getSerializedName();
            if (name.equals("custom")) {
                custom = instrument;
                continue;
            }
            if (rosen$isBlockInTag(name + "_noteblocks")){
                currentInstrument = instrument;
                break;
            }
        }
        for (String customInstrument : CustomInstrumentLoader.customInstruments.keySet()) {
            if (rosen$isBlockInTag(customInstrument + "_noteblocks")){
                currentInstrument = custom;
                break;
            }
        }

        cir.setReturnValue(currentInstrument);
    }

    @Unique
    private boolean rosen$isBlockInTag(String tagName) {
        TagKey<Block> tag = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, tagName));
        BlockBehaviour.BlockStateBase thisObject = (BlockBehaviour.BlockStateBase)(Object)this;
        return thisObject.is(tag);
    }
}