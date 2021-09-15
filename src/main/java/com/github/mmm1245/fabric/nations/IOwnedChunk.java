package com.github.mmm1245.fabric.nations;

import java.util.UUID;

public interface IOwnedChunk {
    public boolean isOwned();
    public UUID owner();
    public void setOwner(UUID owner);
}
