package magicmount;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static ModState createModState(NbtCompound tag) {
        // reverse opening the ModState and
        ModState state = new ModState();
        NbtCompound modState = tag.getCompound(MYTHIC_MOD_ID);
        NbtCompound locations =modState.getCompound("locations");
        var uids =locations.getKeys();
        uids.forEach((uid)->{
            state.startLocations.put(uid,state.convertByteToVec(locations.getByteArray(uid)));
        });
        return state;
    }

    public void updateOriginCoordinates(Vec3d currPos, String uuid) {
        startLocations.put(uuid,currPos);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // save all Server States
        NbtCompound modState = new NbtCompound();
        NbtCompound locations = new NbtCompound();
        this.startLocations.forEach((key,vector) -> {
            locations.putByteArray(key.toString(),this.convertVecToByte(vector));
        });
        modState.put("locations",locations);
        nbt.put(MYTHIC_MOD_ID,modState);
        return nbt;
    }

    private Vec3d convertByteToVec(byte[] bytes) {
        String converted = new String(bytes, StandardCharsets.US_ASCII);
        int i =0;
        int index = converted.indexOf(",",i);
        double x = Double.valueOf(converted.substring(i, index));
        i = index+1;
        index = converted.indexOf(",",i);
        double y = Double.valueOf(converted.substring(i, index));
        i = index+1;
        double z = Double.valueOf(converted.substring(i, converted.length()));
        return new Vec3d(x,y,z);
    }

    private byte[] convertVecToByte(Vec3d vector) {
        String byteString = String.valueOf(vector.x) +','+ vector.y + ',' + vector.z;
        return byteString.getBytes();
    }


    // get the public mod state
    public static ModState getModState(MinecraftServer server) {
        PersistentStateManager pers = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        ModState state = pers.getOrCreate(type,MYTHIC_MOD_ID);
        state.markDirty();
        return state;
    }
}
