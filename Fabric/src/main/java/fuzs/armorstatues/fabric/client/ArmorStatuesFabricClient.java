package fuzs.armorstatues.fabric.client;

import fuzs.armorstatues.common.ArmorStatues;
import fuzs.armorstatues.common.client.ArmorStatuesClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class ArmorStatuesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(ArmorStatues.MOD_ID, ArmorStatuesClient::new);
    }
}
