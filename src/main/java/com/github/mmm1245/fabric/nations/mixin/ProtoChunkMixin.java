package com.github.mmm1245.fabric.nations.mixin;

import com.github.mmm1245.fabric.nations.IOwnedChunk;
import net.minecraft.world.chunk.ProtoChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(ProtoChunk.class)
public class ProtoChunkMixin implements IOwnedChunk {
    @Unique
    protected UUID nations$owner;
    @Override
    public boolean isOwned() {
        return nations$owner != null;
    }
    @Override
    public UUID owner() {
        return nations$owner;
    }
    @Override
    public void setOwner(UUID owner) {
        this.nations$owner = owner;
    }
}
