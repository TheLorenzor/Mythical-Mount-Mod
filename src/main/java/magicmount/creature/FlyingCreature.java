package magicmount.creature;

import magicmount.SittingStates;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

abstract public class FlyingCreature extends FlyingEntity{

    protected boolean is_domesticated = false;
    protected SittingStates current_state = SittingStates.FLYING;
    public FlyingCreature(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    class FlyingRandomGoal extends Goal {

        @Override
        public boolean canStart() {
            return !FlyingCreature.this.is_domesticated;
        }
    }

}
