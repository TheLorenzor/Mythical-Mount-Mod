package magicmount.creature;

import magicmount.ModState;
import magicmount.CreatureStates;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;

import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Dragon extends FlyingCreature {

    private Vec3d startingPoint;

    public Dragon(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);

        this.experiencePoints = 5000;
        // save the starting and spawning point because the dragon is supposed to
        // spawn in a castle and use it as a returning point
        if (!world.isClient) {
            var state = ModState.getModState(world.getServer());
            state.updateOriginCoordinates(this.getPos(), this.uuid.toString());
            this.startingPoint = this.getPos();
        }
        this.current_state = CreatureStates.FLYING;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(6,new RoamAroundGoal(this));
    }

    public static EntityDimensions getDimension() {
        return new EntityDimensions(1f, 1f, false);
    }

    protected void goingToSleep() {
        this.current_state = CreatureStates.SLEEPING;

    }

    public class RoamAroundGoal extends Goal {

        private final Dragon dragon;
        public RoamAroundGoal(Dragon dragon) {
            this.dragon = dragon;
        }

        @Override
        public boolean canStart() {
            return !this.dragon.is_domesticated && this.dragon.current_state != CreatureStates.SLEEPING;
        }

        @Override
        public void start() {
            super.start();
            this.dragon.current_state = CreatureStates.FLYING;
        }
    }

    public class AttackPlayerGoal extends Goal {
        private final Dragon dragon;

        public AttackPlayerGoal(Dragon dragon) {
            this.dragon = dragon;
        }

        @Override
        public boolean canStart() {
            return false;
        }

        @Override
        public void tick() {
            World world = this.dragon.getWorld();

            FireballEntity fireballEntity = new FireballEntity(world, (LivingEntity) this.dragon, 0, 0, -3, 1);
            fireballEntity.setPosition(this.dragon.getPos().add(2, 2, 2));
            world.spawnEntity(fireballEntity);

        }
    }

}
