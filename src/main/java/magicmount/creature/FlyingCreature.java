package magicmount.creature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

abstract public class FlyingCreature extends FlyingEntity{

    public FlyingCreature(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }
}
