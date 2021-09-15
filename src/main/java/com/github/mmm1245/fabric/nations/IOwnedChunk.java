package com.github.mmm1245.fabric.nations;

import java.util.UUID;

public interface IOwnedChunk {
    boolean isOwned();
    UUID owner();
    void setOwner(UUID owner);
}
