package magicmount.creature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

abstract public class FlyingCreature extends FlyingEntity{

    public FlyingCreature(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        Vec3d vec3d = new Vec3d(1.0f,0,0);
        if (this.getVelocity().x != 1.0f) {
            this.setVelocity(vec3d);
        }
    }
}
