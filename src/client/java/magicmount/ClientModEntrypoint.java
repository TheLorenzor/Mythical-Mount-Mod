package magicmount;

import magicmount.models.DragonModel;
import magicmount.renderer.DragonRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClientModEntrypoint implements ClientModInitializer {

    public static final EntityModelLayer MODEL_DRAGON_LAYER = new EntityModelLayer(new Identifier("magical-mount","dragon"),"main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(EntrypointServer.DRAGON, DragonRender::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL_DRAGON_LAYER, DragonModel::getTexturedModelData);

    }
}
