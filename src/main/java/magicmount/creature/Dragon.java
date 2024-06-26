package magicmount.creature;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

import java.awt.*;

public class Dragon extends FlyingCreature{
    public Dragon(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static EntityDimensions getDimension() {
        return EntityDimensions.fixed(1f,1f);
    }
}
