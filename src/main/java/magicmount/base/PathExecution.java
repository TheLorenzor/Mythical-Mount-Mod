package magicmount.base;

import net.minecraft.util.math.Vec3d;

public class PathExecution {



    public void setPath(Path path) {
        Vec3d[] points = new Vec3d[path.getLength()];
        path.getNext();
    }

    public Vec3d getNextPoint(double speed) throws NullPointerException {
        return null;
    }

    public Vec3d getNextAcceleration(double speed) {
        return null;
    }
}
