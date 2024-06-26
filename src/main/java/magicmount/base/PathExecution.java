package magicmount.base;

import net.minecraft.util.math.Vec3d;

public class PathExecution {

    private Vec3d[] splinePoints;
    private int index;

    public PathExecution() {
        this.index = 0;
        this.splinePoints = new Vec3d[5];
        this.splinePoints[0] = Vec3d.ZERO;
        this.splinePoints[1] = new Vec3d(30, 5, 0);
        this.splinePoints[2] = new Vec3d(30, 15, 30);
        this.splinePoints[3] = new Vec3d(0, 25, 30);
        this.splinePoints[4] = new Vec3d(0, 35, 0);

    }

    public void setPath(Path path, Vec3d origin) {
        int length = path.getLength();
        this.splinePoints = new Vec3d[length];
        for (int i = 0; i < length; ++i) {
            this.splinePoints[i] = path.getNext().add(origin);
        }
    }

    /**
     * @param speed in m/s // blocks/ second --> player can run 5.6 blocks / second as reference --> walking: 4.317 blocks/ second
     */
    public Vec3d getVelocity(double speed, Vec3d currPos) throws NullPointerException {
        Vec3d dest = this.splinePoints[(this.index + 1) % this.splinePoints.length];
        Vec3d newSpeed = dest.subtract(currPos).normalize().multiply(0.1*speed);
        Vec3d generalDirection = dest.subtract(this.splinePoints[this.index]).normalize();
        return newSpeed;
    }

}
