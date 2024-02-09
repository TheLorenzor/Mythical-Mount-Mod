package magicmount.creature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class FlyingCreature extends FlyingEntity {
    protected FlyingCreature(EntityType<? extends MobEntity> entityType, World world) {
        super((EntityType<? extends FlyingEntity>) entityType, world);
    }
}
