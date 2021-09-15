package com.github.mmm1245.fabric.nations;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.world.chunk.Chunk;

public class NationsMain implements ModInitializer {
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(CommandManager.literal("nclaim").executes(context -> {
				try {
					ServerPlayerEntity pl = context.getSource().getPlayer();
					Chunk chunk = pl.getServerWorld().getChunk(pl.getChunkPos().getStartPos());
					((IOwnedChunk) chunk).setOwner(pl.getUuid());
					chunk.setShouldSave(true);
				} catch(Exception e){
					e.printStackTrace();
				}
				return 1;
			}));
			dispatcher.register(CommandManager.literal("nowner").executes(context -> {
				try{
					ServerPlayerEntity pl = context.getSource().getPlayer();
					Chunk chunk = pl.getServerWorld().getChunk(pl.getChunkPos().getStartPos());
					IOwnedChunk owned = ((IOwnedChunk) chunk);
					if(owned.isOwned()){
						pl.sendMessage(new LiteralText("Chunk is claimed by " + owned.owner().toString()),false);
					} else {
						pl.sendMessage(new LiteralText("Chunk is not claimed").styled(style -> style.withColor(Formatting.DARK_RED)),false);
					}
				} catch(Exception e){
					e.printStackTrace();
				}
				return 1;
			}));
		});

	}
}
