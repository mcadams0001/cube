package adam.dto;

public class PointXY implements Point {
    protected int x;
    protected int y;

    public PointXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointXY pointXY = (PointXY) o;

        if (x != pointXY.x) return false;
        return y == pointXY.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ']';
    }
}
