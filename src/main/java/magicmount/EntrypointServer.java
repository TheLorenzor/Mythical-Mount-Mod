package magicmount;

import magicmount.creature.FlyingCreature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntrypointServer implements ModInitializer {

    public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());
    public static final EntityType<FlyingCreature> CUBE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("entitytesting", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FlyingCreature::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("tutorial", "custom_item"), CUSTOM_ITEM);
        FabricDefaultAttributeRegistry.register(CUBE, FlyingCreature.createMobAttributes());
    }
}
