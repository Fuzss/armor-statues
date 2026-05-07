package fuzs.armorstatues.common.proxy;

import fuzs.statuemenus.common.api.v1.world.entity.decoration.StatueEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ServerProxy implements Proxy {

    @Override
    public void openStatueScreen(LivingEntity livingEntity, StatueEntity statueEntity, Player player) {
        // NO-OP
    }
}
