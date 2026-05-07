package fuzs.armorstatues.common.proxy;

import fuzs.puzzleslib.common.api.core.v1.ModLoaderEnvironment;
import fuzs.statuemenus.common.api.v1.world.entity.decoration.StatueEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public interface Proxy {
    Proxy INSTANCE = ModLoaderEnvironment.INSTANCE.isClient() ? new ClientProxy() : new ServerProxy();

    void openStatueScreen(LivingEntity livingEntity, StatueEntity statueEntity, Player player);
}
