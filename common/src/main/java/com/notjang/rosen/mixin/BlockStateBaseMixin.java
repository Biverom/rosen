package com.notjang.rosen.mixin;

import com.notjang.rosen.Constants;
import com.notjang.rosen.RosenInstrumentContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin {

    @Inject(method = "instrument()Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;", at = @At("RETURN"), cancellable = true)
    private void instrument(CallbackInfoReturnable<NoteBlockInstrument> cir) {
        NoteBlockInstrument currentInstrument = cir.getReturnValue();

        for (NoteBlockInstrument instrument : NoteBlockInstrument.values()) {
            if (rosen$isBlockInTag(instrument.getSerializedName() + "_noteblocks")){
                currentInstrument = instrument;
                break;
            }
        }

        cir.setReturnValue(currentInstrument);
    }

    @Unique
    private boolean rosen$isBlockInTag(String tagName) {
        TagKey<Block> tag = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, tagName));
        BlockBehaviour.BlockStateBase thisObject = (BlockBehaviour.BlockStateBase)(Object)this;
        return thisObject.is(tag);
    }
}
