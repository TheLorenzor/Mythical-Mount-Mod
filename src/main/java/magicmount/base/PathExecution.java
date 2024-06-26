package magicmount.base;

import net.minecraft.util.math.Vec3d;

public class PathExecution {

    private Vec3d[] splinePoints;
    private int index;

    public PathExecution() {
        this.index =0 ;
    }
    public void setPath(Path path,Vec3d origin) {
        int length = path.getLength();
        this.splinePoints = new Vec3d[length];
        for (int i =0;i<length;++i) {
            this.splinePoints[i] = path.getNext().add(origin);
        }
    }

    /**
     * @param speed in m/s // blocks/ second --> player can run 5.6 blocks / second as reference
     * */
    public Vec3d getVelocity(double speed,Vec3d currPos) throws NullPointerException {
        Vec3d origin = this.splinePoints[index];
        Vec3d dest = this.splinePoints[index+1];
        if(index+2 < this.splinePoints.length) {
            Vec3d dest2 =this.splinePoints[index+2];
        }
        return Vec3d.ZERO;
    }

}
