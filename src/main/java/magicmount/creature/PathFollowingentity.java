package magicmount.creature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public abstract class PathFollowingentity extends PathAwareEntity {
    protected PathFollowingentity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
}
