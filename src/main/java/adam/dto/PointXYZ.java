package adam.dto;

public class PointXYZ extends PointXY {
    private int z;

    public PointXYZ(int x, int y, int z) {
        super(x,y);
        this.z = z;
    }
    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PointXYZ pointXYZ = (PointXYZ) o;

        return z == pointXYZ.z;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }
}
