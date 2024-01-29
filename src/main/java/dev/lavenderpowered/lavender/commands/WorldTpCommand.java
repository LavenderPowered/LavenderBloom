package dev.lavenderpowered.lavender.commands;

import dev.lavenderpowered.lavender.bloom.InstanceRegistry;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class WorldTpCommand extends Command {
    public WorldTpCommand() {
        super("worldtp", "instancetp");
        setCondition((sender, commandString) -> sender.hasPermission(Permissions.WORLD_TP));
        var worldName = ArgumentType.String("world-name");
        addSyntax(((sender, context) -> {
            final String world = context.get(worldName);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // Find a way to not teleport player into block, if coordinates are inside of a block
                player.setInstance(InstanceRegistry.getInstanceForName(world));
                player.sendMessage("§5Teleported to: §d§l" + world);
            }
        }), worldName);
    }
}