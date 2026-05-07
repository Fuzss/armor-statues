package fuzs.armorstatues;

import fuzs.armorstatues.config.ClientConfig;
import fuzs.armorstatues.handler.ArmorStandInteractHandler;
import fuzs.armorstatues.init.ModRegistry;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.event.v1.core.EventPhase;
import fuzs.puzzleslib.common.api.event.v1.entity.player.PlayerInteractEvents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorStatues implements ModConstructor {
    public static final String MOD_ID = "armorstatues";
    public static final String MOD_NAME = "Armor Statues";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).client(ClientConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        // Use a high priority, so we run before other mods that add armor stand interactions.
        // We require an empty hand + any shift key to be held, so those other mods can still run their behaviors when those conditions are not met.
        PlayerInteractEvents.USE_ENTITY.register(EventPhase.BEFORE, ArmorStandInteractHandler::onUseEntity);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
