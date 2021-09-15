package com.github.mmm1245.fabric.nations.mixin;

import com.github.mmm1245.fabric.nations.IOwnedChunk;
import net.minecraft.world.chunk.ReadOnlyChunk;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(ReadOnlyChunk.class)
public class ReadOnlyChunkMixin implements IOwnedChunk {
    @Shadow @Final private WorldChunk wrapped;
    @Override
    public boolean isOwned() {
        return ((IOwnedChunk)wrapped).isOwned();
    }
    @Override
    public UUID owner() {
        return ((IOwnedChunk)wrapped).owner();
    }
    @Override
    public void setOwner(UUID owner) {
        ((IOwnedChunk)wrapped).setOwner(owner);
    }
}
