package fuzs.armorstatues.common.world.entity.decoration;

import fuzs.armorstatues.common.world.inventory.data.ArmorStandScreenTypes;
import fuzs.statuemenus.common.api.v1.world.entity.decoration.ArmorStandStatue;
import fuzs.statuemenus.common.api.v1.world.inventory.data.StatueScreenType;
import net.minecraft.world.entity.decoration.ArmorStand;

import java.util.List;

public record ArmorStandStatueImpl(ArmorStand armorStand) implements ArmorStandStatue {

    @Override
    public List<StatueScreenType> getScreenTypes() {
        return ArmorStandScreenTypes.TYPES;
    }
}
