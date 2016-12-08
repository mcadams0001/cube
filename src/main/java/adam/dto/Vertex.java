package adam.dto;

/**
 * Represents a vertex in a block being on each end of edge.
 */
public class Vertex {
    private int id;
    /**
     * Point in space which identifies vertex location.
     */
    private Point point;
    /**
     * State of the vertex (shape). If set to 1 the vertex is filled otherwise 0.
     */
    private char shape = '0';

    public Vertex(int id, Point point) {
        this.id = id;
        this.point = point;
    }

    public Vertex(int id, Point point, char shape) {
        this.id = id;
        this.point = point;
        this.shape = shape;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    public char getShape() {
        return shape;
    }

    public void setShape(char shape) {
        this.shape = shape;
    }

    public void clear() {
        this.setShape('0');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (id != vertex.id) return false;
        if (shape != vertex.shape) return false;
        return !(point != null ? !point.equals(vertex.point) : vertex.point != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (int) shape;
        return result;
    }


    @Override
    public String toString() {
        return point.toString() + "=" + shape;
    }
}
