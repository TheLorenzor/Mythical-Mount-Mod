package magicmount.creature;

import magicmount.CreatureStates;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

abstract public class FlyingCreature extends FlyingEntity {

    protected boolean is_domesticated = false;
    protected CreatureStates current_state = CreatureStates.SLEEPING;

    public FlyingCreature(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new FlyingRandomGoal());
    }

    private class FlyingRandomGoal extends Goal {
        Vec3d originalPosition = Vec3d.ZERO;
        private float radius = 10;
        private boolean isOutOfBounce;

        @Override
        public boolean canStart() {
            System.out.println(!FlyingCreature.this.is_domesticated && FlyingCreature.this.current_state == CreatureStates.FLYING);
            return !FlyingCreature.this.is_domesticated && FlyingCreature.this.current_state == CreatureStates.FLYING;
        }

        @Override
        public void start() {
            // Remembers on start the current location and then establishes a Radius in which  the dragon flies randomly
            originalPosition = FlyingCreature.this.getPos();
        }

        @Override
        public void tick() {
            Random rand = FlyingCreature.this.random;
            if (rand.nextInt(this.getTickCount(150)) == 0) {
                Vec3d vectorBackinRing = this.originalPosition.subtract(FlyingCreature.this.getPos());
                if (!this.isOutOfBounce) {
                    float x = rand.nextFloat();
                    if (rand.nextBoolean()) {
                        x = x * -1;
                    }
                    float y = rand.nextFloat();
                    if (rand.nextBoolean()) {
                        y = y * -1;
                    }
                    float z = rand.nextFloat();
                    if (rand.nextBoolean()) {
                        z = z * -1;
                    }
                    Vec3d vector = new Vec3d(x, y, z);
                    FlyingCreature.this.setVelocity(vector);
                } else {
                    FlyingCreature.this.setVelocity(vectorBackinRing.normalize());
                    this.isOutOfBounce = false;

                }
                if (vectorBackinRing.length() > this.radius) {
                    this.isOutOfBounce = true;
                }
            }
        }
    }
}
