package dev.compactmods.machines.room.network;

import dev.compactmods.machines.room.RoomHelper;
import dev.compactmods.machines.room.capability.PlayerRoomMetadataProviderProvider;
import dev.compactmods.machines.room.client.ClientRoomMetadata;
import dev.compactmods.machines.room.client.MachineRoomScreen;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;

public class ClientRoomNetworkHandler {
    public static void handleBlockData(InitialRoomBlockDataPacket blockData) {
        final var mc = Minecraft.getInstance();
        if(mc.screen instanceof MachineRoomScreen mrs) {
            mrs.getMenu().setBlocks(blockData.blocks());
            mrs.updateBlockRender();
        }
    }

    public static void handleRoomSync(SyncRoomMetadataPacket sync) {
        final var mc = Minecraft.getInstance();
        PlayerRoomMetadataProviderProvider.CURRENT_ROOM_META.maybeGet(mc.player).ifPresent(meta -> {
            if(sync.owner().equals(Util.NIL_UUID))
                meta.getCurrentRoomMetadataProvider().clearCurrent();
            else
                meta.getCurrentRoomMetadataProvider().setCurrent(new ClientRoomMetadata(sync.roomCode(), sync.owner()));
        });
    }
}
