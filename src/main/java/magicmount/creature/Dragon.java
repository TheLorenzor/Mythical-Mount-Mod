package magicmount.creature;

import magicmount.ModState;
import magicmount.CreatureStates;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;

import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class Dragon extends FlyingCreature {

    private Vec3d startingPoint;
    private boolean test;

    public Dragon(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.test = false;
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

    @Override
    public void tick() {
        super.tick();
        if (this.random.nextInt(20000) == 69 && !test) {
            this.setVelocity(0,0,1);
            test = true;
        }

        }

    /**
     * This Goal is the default of the dragon --> it flies in the air for 20 Blocks and then
     * flows around the
    * */
    public class RoamAroundGoal extends Goal {

        private final Dragon dragon;
        private Vec3d midPoint;
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
            this.midPoint = this.dragon.getPos();
            this.midPoint = this.midPoint.add(new Vec3d(0,20,0));
        }

        @Override
        public void tick() {
            if (this.dragon.getPos().z<this.midPoint.z) {

            } else {

            }
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
