package fuzs.armorstatues.neoforge.client;

import fuzs.armorstatues.common.ArmorStatues;
import fuzs.armorstatues.common.client.ArmorStatuesClient;
import fuzs.armorstatues.common.data.client.ModLanguageProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = ArmorStatues.MOD_ID, dist = Dist.CLIENT)
public class ArmorStatuesNeoForgeClient {

    public ArmorStatuesNeoForgeClient() {
        ClientModConstructor.construct(ArmorStatues.MOD_ID, ArmorStatuesClient::new);
        DataProviderHelper.registerDataProviders(ArmorStatues.MOD_ID, ModLanguageProvider::new);
    }
}
