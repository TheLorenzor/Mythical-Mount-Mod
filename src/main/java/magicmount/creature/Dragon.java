package magicmount.creature;

import magicmount.SittingStates;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;

import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.At;

import java.awt.*;

public class Dragon extends FlyingCreature {

    public Dragon(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5000;

    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(5,new AttackPlayerGoal(this));
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

    protected void goingToSleep() {
        this.current_state = SittingStates.SLEEPING;
        
    }

    public class AttackPlayerGoal extends Goal {
        private final Dragon dragon;

        public AttackPlayerGoal(Dragon dragon) {
            this.dragon = dragon;
        }

        @Override
        public boolean canStart() {
            return true;
        }

        @Override
        public void tick() {
            World world = this.dragon.getWorld();

            FireballEntity fireballEntity = new FireballEntity(world,(LivingEntity) this.dragon,0,0,-3,1);
            fireballEntity.setPosition(this.dragon.getPos().add(2,2,2));
            world.spawnEntity(fireballEntity);

        }
    }

}
