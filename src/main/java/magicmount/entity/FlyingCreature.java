package magicmount.entity;

import magicmount.CreatureStates;
import magicmount.entity.dragon.Dragon;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

abstract public class FlyingCreature extends PathAwareEntity {

    protected boolean is_domesticated = false;
    protected CreatureStates current_state = CreatureStates.SLEEPING;

    public FlyingCreature(EntityType<? extends FlyingCreature> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public boolean canBeLeashed() {
        return is_domesticated;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new FollowPathGoal(this));
    }

    private class FollowPathGoal extends Goal {

        protected Vec3d[] listOfVecs;
        protected int index;

        protected FlyingCreature creature;
        public FollowPathGoal(FlyingCreature creature) {
            this.listOfVecs = new Vec3d[4];
            this.listOfVecs[0] = Vec3d.ZERO;
            this.listOfVecs[1] = new Vec3d(20,0,20);
            this.listOfVecs[2] = new Vec3d(-20,20,20);
            this.listOfVecs[3] = new Vec3d(-20,40,20);
            this.creature = creature;
            this.index =0;
        }
        @Override
        public boolean canStart() {
            return true;
        }

        @Override
        public void start() {
            for (int i=0;i<this.listOfVecs.length;i++) {
                this.listOfVecs[i] = this.listOfVecs[i].add(this.creature.getPos());
            }
            super.start();

        }

        @Override
        public void tick() {

            Vec3d origin = this.listOfVecs[(this.index)%this.listOfVecs.length];
            Vec3d dest = this.listOfVecs[(this.index+1)%this.listOfVecs.length];
            Vec3d direction = dest.subtract(origin);
            Vec3d newVelocity = dest.subtract(this.creature.getPos()).normalize();
            if(newVelocity.dotProduct(direction)<=0.1) {
                if (this.index==3) {
                    this.index =0;
                } else {
                    this.index++;
                }
                this.creature.setPos(dest.x,dest.y,dest.z);
            } else {

                this.creature.setVelocity(newVelocity);
            }
            super.tick();
        }
    }

    public class RoamAroundGoal extends Goal {

        private final Dragon dragon;
        private Vec3d midPoint;
        public RoamAroundGoal(Dragon dragon) {
            this.dragon = dragon;
        }

        @Override
        public boolean canStart() {
            return false;
        }

        @Override
        public void start() {
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
