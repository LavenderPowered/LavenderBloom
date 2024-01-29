package dev.lavenderpowered.lavender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minestom.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Settings {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            //.serializeNulls()
            .create();
    private static final File settingsFile = new File("settings.json");
    private static final File worldsFile = new File("worlds.json");

    private static SettingsState currentSettings = null;
    private static WorldsState currentWorlds = null;

    public static void read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(settingsFile));
            currentSettings = gson.fromJson(reader, SettingsState.class);
        } catch (FileNotFoundException e) {
            currentSettings = new SettingsState();
            try {
                writeSettings();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(worldsFile));
            currentWorlds = gson.fromJson(reader, WorldsState.class);
        } catch (FileNotFoundException e) {
            currentWorlds = new WorldsState();
            try {
                writeWorlds();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeSettings() throws IOException {
        String json = gson.toJson(currentSettings);
        Writer writer = new FileWriter(settingsFile);
        writer.write(json);
        writer.close();
    }

    public static void writeWorlds() throws IOException {
        String json = gson.toJson(currentWorlds);
        Writer writer = new FileWriter(worldsFile);
        writer.write(json);
        writer.close();
    }

    private static class SettingsState {
        private final String SERVER_IP;
        private final int SERVER_PORT;

        private final RunMode MODE;
        private final String VELOCITY_SECRET;

        // JVM arguments
        private final String TPS;
        private final String CHUNK_VIEW_DISTANCE;
        private final String ENTITY_VIEW_DISTANCE;
        private final boolean TERMINAL_DISABLED;

        private final ServerListPlayers LIST_PLAYERS;
        private final boolean EXTS_COMMAND_FOR_PLAYERS;
        private final boolean OPEN_TO_LAN;

        private SettingsState() {
            this.SERVER_IP = "localhost";
            this.SERVER_PORT = 25565;

            this.MODE = RunMode.OFFLINE;
            this.VELOCITY_SECRET = "";

            this.TPS = "20";
            this.CHUNK_VIEW_DISTANCE = "8";
            this.ENTITY_VIEW_DISTANCE = "5";
            this.TERMINAL_DISABLED = false;

            this.LIST_PLAYERS = ServerListPlayers.SHOW;
            this.EXTS_COMMAND_FOR_PLAYERS = false;
            this.OPEN_TO_LAN = false;
        }
    }

    private static class WorldsState {
        private final boolean ENABLE_INSTANCE;
        private final WorldType WORLD_TYPE;
        private final List<String> WORLD_LOCATIONS;
        private final boolean WORLDTP_PLAYERS_IN_BLOCKS;

        private WorldsState() {
            this.ENABLE_INSTANCE = false;
            this.WORLD_TYPE = WorldType.FLAT;
            this.WORLD_LOCATIONS = new ArrayList<>();
            this.WORLDTP_PLAYERS_IN_BLOCKS = false;
        }
    }

    public enum RunMode {
        OFFLINE("offline"),
        ONLINE("online"),
        BUNGEECORD("BungeeCord"),
        VELOCITY("Velocity");

        private final String name;

        RunMode(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum WorldType {
        FLAT("flat"),
        ANVIL("anvil"),
        POLAR("polar"),
        POLAR_CONVERT("polar_convert");

        private final String name;

        WorldType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum ServerListPlayers {
        SHOW("show"),
        HIDE("hide");

        private final String name;

        ServerListPlayers(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static RunMode getMode() { return currentSettings.MODE; }

    public static String getServerIp() {
        return System.getProperty("server.ip", currentSettings.SERVER_IP);
    }
    public static int getServerPort() {
        int port = Integer.getInteger("server.port", currentSettings.SERVER_PORT);
        if (port < 1) return 25565;
        return port;
    }

    public static boolean hasVelocitySecret() {
        return !currentSettings.VELOCITY_SECRET.isBlank();
    }

    public static String getVelocitySecret() {
        return currentSettings.VELOCITY_SECRET;
    }

    public static String getTps() {
        return currentSettings.TPS;
    }
    public static String getChunkViewDistance() {
        return currentSettings.CHUNK_VIEW_DISTANCE;
    }
    public static String getEntityViewDistance() {
        return currentSettings.ENTITY_VIEW_DISTANCE;
    }
    public static boolean isTerminalDisabled() {
        return currentSettings.TERMINAL_DISABLED;
    }

    public static boolean extCommandEnabled() {
        return currentSettings.EXTS_COMMAND_FOR_PLAYERS;
    }

    public static boolean isOpenToLAN() {
        return currentSettings.OPEN_TO_LAN;
    }

    public static boolean isInstanceEnabled() {
        return currentWorlds.ENABLE_INSTANCE;
    }
    public static WorldType getWorldType() {
        return currentWorlds.WORLD_TYPE;
    }
    public static ServerListPlayers getServerListPlayers() {
        return currentSettings.LIST_PLAYERS;
    }
    public static List<String> getWorldLocations() { 
        return currentWorlds.WORLD_LOCATIONS; 
    }
    public static boolean getWorldTpPlayers() {
        return currentWorlds.WORLDTP_PLAYERS_IN_BLOCKS;
    }
}
