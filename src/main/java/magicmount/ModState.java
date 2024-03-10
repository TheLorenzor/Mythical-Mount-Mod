package magicmount;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static magicmount.EntrypointServer.MYTHIC_MOD_ID;

public class ModState extends PersistentState {


    // is used to have the type be used universal between all stuff
    private static Type<ModState> type = new Type<>(
            ModState::new,
            ModState::createModState,
            null
    );

    private HashMap<String,Vec3d> startLocations = new HashMap<>();
    private ArrayList<String> registeredEntitys = new ArrayList<>();
    public static ModState createModState(NbtCompound tag) {
        ModState state = new ModState();
        return state;
    }

    public void addOriginCoordinates(Vec3d currPos, String uuid) {
        List<String> results = registeredEntitys.stream().filter(uuidToSearch->!uuid.equals(uuidToSearch)).collect(Collectors.toList());
        if (results.isEmpty()) {
            registeredEntitys.add(uuid);
            startLocations.put(uuid,currPos);
        }
    }
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // save all Server States
        NbtCompound modState = new NbtCompound();
        NbtCompound locations = new NbtCompound();

        nbt.put(MYTHIC_MOD_ID,modState);
        return null;
    }

    // get the public mod state
    public static ModState getModState(MinecraftServer server) {
        PersistentStateManager pers = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        ModState  state = pers.getOrCreate(type,MYTHIC_MOD_ID);
        state.markDirty();
        return state;
    }
}
