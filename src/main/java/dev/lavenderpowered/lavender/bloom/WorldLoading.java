package dev.lavenderpowered.lavender.bloom;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;

public class WorldLoading {
    public static void loadAnvil(InstanceManager instanceManager, String worldLocation) {
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        instanceContainer.setChunkLoader(new AnvilLoader(worldLocation));
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            // Figure out how to get spawn coordinates
            player.setRespawnPoint(new Pos(0, 250, 0));
        });
    }

    public static void loadPolar(InstanceManager instanceManager, String worldLocation) {
        // To be added
    }
}
