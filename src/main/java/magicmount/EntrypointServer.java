package magicmount;

import magicmount.creature.Dragon;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntrypointServer implements ModInitializer {


    public static final String MYTHIC_MOD_ID = "magical-mount";
    public static final EntityType<Dragon> DRAGON = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MYTHIC_MOD_ID,"dragon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,Dragon::new).dimensions(Dragon.getDimension()).build());
    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(DRAGON, Dragon.createMobAttributes());
    }

    private static void createItem() {

    }
}
