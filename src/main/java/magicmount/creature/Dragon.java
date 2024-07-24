package magicmount.creature;

import magicmount.CreatureStates;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Dragon extends FlyingCreature {

    private Vec3d startingPoint;

    public Dragon(EntityType<? extends FlyingCreature> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5000;
        // save the starting and spawning point because the dragon is supposed to
        // spawn in a castle and use it as a returning point
        this.current_state = CreatureStates.FLYING;
    }
    public static EntityDimensions getDimension() {
        return EntityDimensions.fixed(1f,1f);
    }

    protected void goingToSleep() {
        this.current_state = CreatureStates.SLEEPING;

    }
    /**
     * This Goal is the default of the dragon --> it flies in the air for 20 Blocks and then
     * flows around the
    * */


}
