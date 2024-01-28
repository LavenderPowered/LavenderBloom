package dev.lavenderpowered.lavender.commands;

import net.minestom.server.permission.Permission;

public class Permissions {
    public static final Permission SHUTDOWN = new Permission("lavender.shutdown");
    public static final Permission RESTART = new Permission("lavender.restart");
    public static final Permission EXTENSIONS = new Permission("lavender.exts");
    public static final Permission SERVER_INFO = new Permission("lavender.svinfo");
}
