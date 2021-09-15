package com.github.mmm1245.fabric.nations.mixin;

import com.github.mmm1245.fabric.nations.IOwnedChunk;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkSerializer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.poi.PointOfInterestStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSerializer.class)
public class ChunkSerializationMixin {
    private static String chunkOwnerKey = "ChunkOwner";
    @Inject(at = @At("RETURN"),method = "serialize",cancellable = true)
    private static void serialize(ServerWorld world, Chunk chunk, CallbackInfoReturnable<NbtCompound> cir){
        if(((IOwnedChunk)chunk).isOwned()) {
            cir.getReturnValue().putUuid(chunkOwnerKey, ((IOwnedChunk) chunk).owner());
        }

    }
    @Inject(at = @At("RETURN"),method = "deserialize",cancellable = true)
    private static void deserialize(ServerWorld world, StructureManager structureManager, PointOfInterestStorage poiStorage, ChunkPos pos, NbtCompound nbt, CallbackInfoReturnable<ProtoChunk> cir){
        if(nbt.contains(chunkOwnerKey)) {
            Chunk chunk = cir.getReturnValue();
            ((IOwnedChunk) chunk).setOwner(nbt.getUuid(chunkOwnerKey));
            cir.setReturnValue((ProtoChunk) chunk);
        }
        else
            ((IOwnedChunk)cir.getReturnValue()).setOwner(null);
    }
}
