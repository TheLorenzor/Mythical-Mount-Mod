package magicmount.renderer;

import magicmount.ClientModEntrypoint;
import magicmount.creature.Dragon;
import magicmount.models.DragonModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class DragonRender extends MobEntityRenderer<Dragon, DragonModel> {
    public DragonRender(EntityRendererFactory.Context context) {
        super(context, new DragonModel(context.getPart(ClientModEntrypoint.MODEL_DRAGON_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(Dragon entity) {
        return new Identifier("magical-mounts", "cube.png");    }
}
