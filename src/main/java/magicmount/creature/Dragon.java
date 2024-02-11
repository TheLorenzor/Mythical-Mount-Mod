package magicmount.creature;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

import java.awt.*;

public class Dragon extends FlyingCreature {

    public Dragon(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    public static EntityDimensions getDimension() {
        return new EntityDimensions(1f,1f,false);
    }
}
