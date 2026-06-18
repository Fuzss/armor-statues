package fuzs.armorstatues.common.proxy;

import fuzs.armorstatues.common.ArmorStatues;
import fuzs.armorstatues.common.config.ClientConfig;
import fuzs.armorstatues.common.network.client.data.CommandDataSyncHandler;
import fuzs.armorstatues.common.network.client.data.VanillaTweaksDataSyncHandler;
import fuzs.statuemenus.common.api.v1.client.gui.screens.StatueScreenFactory;
import fuzs.statuemenus.common.api.v1.network.client.data.DataSyncHandler;
import fuzs.statuemenus.common.api.v1.world.entity.decoration.StatueEntity;
import fuzs.statuemenus.common.api.v1.world.inventory.StatueHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ClientProxy extends ServerProxy {

    @Override
    public void openStatueScreen(LivingEntity livingEntity, StatueEntity statueEntity, Player player) {
        StatueHolder statueHolder = StatueHolder.simple(livingEntity, statueEntity);
        Screen screen = StatueScreenFactory.createLastScreenType(statueHolder,
                player.getInventory(),
                livingEntity.getDisplayName(),
                createDataSyncHandler(statueHolder, (LocalPlayer) player));
        Minecraft.getInstance().gui.setScreen(screen);
    }

    private static DataSyncHandler createDataSyncHandler(StatueHolder holder, LocalPlayer player) {
        if ((!player.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER) || ArmorStatues.CONFIG.get(
                ClientConfig.class).overrideClientPermissionsCheck)
                && ArmorStatues.CONFIG.get(ClientConfig.class).useVanillaTweaksTriggers) {
            return new VanillaTweaksDataSyncHandler(holder, player);
        } else {
            return new CommandDataSyncHandler(holder, player);
        }
    }
}
