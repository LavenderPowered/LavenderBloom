package dev.lavenderpowered.lavender.bloom;

import dev.lavenderpowered.lavender.Server;
import net.hollowcube.polar.AnvilPolar;
import net.hollowcube.polar.PolarLoader;
import net.hollowcube.polar.PolarWorld;
import net.hollowcube.polar.PolarWriter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        InstanceRegistry.registerInstance(instanceContainer, worldLocation);
    }

    public static void loadPolar(InstanceManager instanceManager, String worldLocation) throws IOException {
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        instanceContainer.setChunkLoader(new PolarLoader(Path.of(worldLocation + ".polar")));
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            // Figure out how to get spawn coordinates
            player.setRespawnPoint(new Pos(0, 250, 0));
        });
        InstanceRegistry.registerInstance(instanceContainer, worldLocation);
    }

    public static void anvilToPolar(String worldFolder) throws IOException {
        Path path = Paths.get(".");
        String worldDir = path.toAbsolutePath()
                .toString()
                .substring(0, path.toAbsolutePath().toString().lastIndexOf("/") + 1)
                + "polar_conversion"
                + File.separator
                + worldFolder;

        if (Files.isDirectory(Path.of(worldDir))) {
            PolarWorld polarWorld = AnvilPolar.anvilToPolar(Path.of(worldDir));
            if (polarWorld != null) {
                byte[] polarWorldBytes = PolarWriter.write(polarWorld);

                try (FileOutputStream outputStream = new FileOutputStream(worldDir + ".polar")) {
                    outputStream.write(polarWorldBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
