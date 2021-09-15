package com.github.mmm1245.fabric.nations.mixin;

import com.github.mmm1245.fabric.nations.IOwnedChunk;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.UUID;

@Mixin(Chunk.class)
public interface ChunkMixin extends IOwnedChunk{

}
