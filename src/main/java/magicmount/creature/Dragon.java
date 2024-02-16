package magicmount.creature;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;

public class Dragon extends FlyingCreature {

    public Dragon(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5000;

    }

    public static EntityDimensions getDimension() {
        return new EntityDimensions(1f,1f,false);
    }


    @Override
    public void tickMovement() {
        super.tickMovement();
        this.getWorld().getProfiler().push("moving around");
        this.getWorld().getProfiler().pop();
    }

    class MineResourcesGoal extends Goal {

        @Override
        public boolean canStart() {
            return false;
        }
    }


}
