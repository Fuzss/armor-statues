package fuzs.armorstatues.common.client;

import fuzs.armorstatues.common.client.gui.screens.armorstand.ArmorStandAlignmentsScreen;
import fuzs.armorstatues.common.client.gui.screens.armorstand.ArmorStandVanillaTweaksScreen;
import fuzs.armorstatues.common.client.gui.screens.armorstand.ArmorStandPositionScreen;
import fuzs.armorstatues.common.client.handler.ClientInteractHandler;
import fuzs.armorstatues.common.client.handler.DataSyncTickHandler;
import fuzs.armorstatues.common.init.ModRegistry;
import fuzs.armorstatues.common.world.inventory.data.ArmorStandScreenTypes;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.MenuScreensContext;
import fuzs.puzzleslib.common.api.client.event.v1.ClientTickEvents;
import fuzs.puzzleslib.common.api.client.event.v1.entity.player.InteractionInputEvents;
import fuzs.puzzleslib.common.api.client.event.v1.gui.ScreenEvents;
import fuzs.puzzleslib.common.api.client.gui.v2.tooltip.ItemTooltipRegistry;
import fuzs.puzzleslib.common.api.event.v1.core.EventPhase;
import fuzs.statuemenus.common.api.v1.client.gui.screens.StatueScreenFactory;
import fuzs.statuemenus.common.api.v1.helper.ArmorStandInteractHelper;
import fuzs.statuemenus.common.api.v1.world.inventory.StatueMenu;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;

public class ArmorStatuesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
        ItemTooltipRegistry.ITEM.registerItemTooltip(Items.ARMOR_STAND,
                ArmorStandInteractHelper.getArmorStandHoverText());
    }

    private static void registerEventHandlers() {
        ClientTickEvents.END.register(DataSyncTickHandler::onEndClientTick);
        ScreenEvents.remove(Screen.class).register(DataSyncTickHandler::onRemove);
        // event phase must match PlayerInteractEvents#USE_ENTITY_AT as both are implemented using the same event on Fabric
        InteractionInputEvents.USE.register(EventPhase.BEFORE, ClientInteractHandler::onUseInteraction);
    }

    @Override
    public void onClientSetup() {
        StatueScreenFactory.register(ArmorStandScreenTypes.POSITION, ArmorStandPositionScreen::new);
        StatueScreenFactory.register(ArmorStandScreenTypes.ALIGNMENTS, ArmorStandAlignmentsScreen::new);
        StatueScreenFactory.register(ArmorStandScreenTypes.VANILLA_TWEAKS, ArmorStandVanillaTweaksScreen::new);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public void onRegisterMenuScreens(MenuScreensContext context) {
        context.registerMenuScreen(ModRegistry.ARMOR_STAND_MENU_TYPE.value(),
                (StatueMenu menu, Inventory inventory, Component component) -> {
                    return StatueScreenFactory.createLastScreenType(menu, inventory, component);
                });
    }
}
