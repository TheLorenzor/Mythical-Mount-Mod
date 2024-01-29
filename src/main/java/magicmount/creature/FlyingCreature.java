package magicmount.creature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class FlyingCreature extends PathAwareEntity {
    public FlyingCreature(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
}
