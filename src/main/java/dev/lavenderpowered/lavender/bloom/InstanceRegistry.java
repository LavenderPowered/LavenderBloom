package dev.lavenderpowered.lavender.bloom;

import net.minestom.server.instance.InstanceContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class InstanceRegistry {
    private static final Map<UUID, String> instanceNames = new HashMap<>();
    private static final Map<String, InstanceContainer> instanceContainers = new WeakHashMap<>();

    public static void registerInstance(InstanceContainer instanceContainer, String name) {
        instanceNames.put(instanceContainer.getUniqueId(), name);
        instanceContainers.put(name, instanceContainer);
    }

    public static  String getNameForInstance(UUID uuid) {
        return instanceNames.get(uuid);
    }

    public static InstanceContainer getInstanceForName(String name) {
        return instanceContainers.get(name);
    }
}
