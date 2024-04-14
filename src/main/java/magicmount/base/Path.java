package magicmount.base;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class Path {

    private ArrayList<Vec3d> listOfPaths;
    private int index = 0;

    public Path() {
        this.listOfPaths = new ArrayList<>();
    }
    public void addPath(Vec3d vec) {
        this.listOfPaths.add(vec);
    }

    public Vec3d getNext() {
        Vec3d nextVec = this.listOfPaths.get(index);
        index++;
        return nextVec;
    }
    public int getLength() {
        return this.listOfPaths.size();
    }
}
