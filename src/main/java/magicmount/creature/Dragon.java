package magicmount.creature;

import magicmount.CreatureStates;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Dragon extends FlyingCreature {

    private Vec3d startingPoint;
    private boolean test;

    public Dragon(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.test = false;
        this.experiencePoints = 5000;
        // save the starting and spawning point because the dragon is supposed to
        // spawn in a castle and use it as a returning point
        this.current_state = CreatureStates.FLYING;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(6,new RoamAroundGoal(this));
    }

    public static EntityDimensions getDimension() {
        return EntityDimensions.fixed(1f,1f);
    }

    protected void goingToSleep() {
        this.current_state = CreatureStates.SLEEPING;

    }

    @Override
    public void tick() {
        if (this.random.nextInt(1000) == 69 || test) {
            if (!test) {
                System.out.println("TEST START");
            }
            test = true;

            this.setVelocity(0,0,0.5d); //  vec of length 1 equals to 10 m/s for 1 sec
        }
        super.tick();

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
    }

}
