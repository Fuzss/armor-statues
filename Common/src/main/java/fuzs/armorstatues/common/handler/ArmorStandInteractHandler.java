package fuzs.armorstatues.common.handler;

import fuzs.armorstatues.common.ArmorStatues;
import fuzs.armorstatues.common.init.ModRegistry;
import fuzs.armorstatues.common.proxy.Proxy;
import fuzs.armorstatues.common.world.entity.decoration.ArmorStandStatueImpl;
import fuzs.puzzleslib.common.api.event.v1.core.EventResultHolder;
import fuzs.puzzleslib.common.api.network.v4.NetworkingHelper;
import fuzs.statuemenus.common.api.v1.helper.ArmorStandInteractHelper;
import fuzs.statuemenus.common.api.v1.world.entity.decoration.StatueEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ArmorStandInteractHandler {

    public static EventResultHolder<InteractionResult> onUseEntity(Player player, Level level, InteractionHand interactionHand, Entity entity, Vec3 hitVector) {
        if (entity.is(EntityTypeIds.ARMOR_STAND) && entity instanceof ArmorStand armorStand) {
            boolean clientsideOnly =
                    level.isClientSide() && !NetworkingHelper.isModPresentServerside(ArmorStatues.MOD_ID);
            // the menu won't exist in the registry if the mod is missing serverside since Forge syncs registries to clients
            MenuType<?> menuType = clientsideOnly ? null : ModRegistry.ARMOR_STAND_MENU_TYPE.value();
            StatueEntity statueEntity = new ArmorStandStatueImpl(armorStand);
            InteractionResult interactionResult = ArmorStandInteractHelper.openStatueMenu(player,
                    level,
                    interactionHand,
                    armorStand,
                    menuType,
                    statueEntity);
            if (interactionResult.consumesAction()) {
                if (clientsideOnly) {
                    Proxy.INSTANCE.openStatueScreen(armorStand, statueEntity, player);
                    // required so no packet is sent to server when only installed client-side, so the server doesn't change any equipment when we only want to open the screen
                    // returning InteractionResult.FAIL will miss out on the player arm swing animation, which we manually play here
                    player.swing(interactionHand);
                    return EventResultHolder.interrupt(InteractionResult.FAIL);
                } else {
                    return EventResultHolder.interrupt(interactionResult);
                }
            }
        }

        return EventResultHolder.pass();
    }
}
