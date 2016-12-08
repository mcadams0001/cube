package adam.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * Class represents a directional edge.
 * The edge spans from vertex1 to vertex2.
 * The edge's state is represented by shape. The shape holds 0 where there are spaces and 1 where filled.
 * The empty shape will have "00000" where the state of first and last element are represented inside vertices.
 * The Edge also holds a reference to opposite one which holds the reversed shape.
 */
public class Edge {
    /**
     * Unique id of a shape.
     */
    private int id;
    /**
     * Edge shape being the middle three items of the edge.
     */
    private String shape = "000";
    /**
     * Opposite shape which spans from vertex2 to vertex1.
     */
    private Edge oppositeEdge;
    /**
     * List of two vertices where vertex1 holds id 0 and vertex2 id 1.
     */
    private List<Vertex> vertices = new LinkedList<>();

    /**
     * Creates a new instance of edge with default empty shape being "00000"
     *
     * @param id   the id of the edge being a unique value.
     * @param vertex1 vertex from which the edge spans to.
     * @param vertex2 vertex to which the edge spans.
     */
    public Edge(int id, Vertex vertex1, Vertex vertex2) {
        this(id, "000", vertex1, vertex2);
    }

    /**
     * Creates a new instance of edge.
     *
     * @param id   the id of the edge being a unique value.
     * @param shape   represented as set of 0 and 1 where 0 is an empty space and one filled.
     * @param vertex1 vertex from which the edge spans to.
     * @param vertex2 vertex to which the edge spans.
     */
    public Edge(int id, String shape, Vertex vertex1, Vertex vertex2) {
        this.shape = shape;
        this.id = id;
        vertices.add(vertex1);
        vertices.add(vertex2);
    }

    /**
     * Creates a new instance of Edge being a mirror fo existing edge.
     *
     * @param edge    edge to be used for creating a mirror.
     * @param vertex1 vertex1 from which the edge spans from.
     * @param vertex2 vertex2 to which the edge spans.
     * @return a new instance of mirrored Edge.
     */
    public static Edge mirroredEdge(Edge edge, Vertex vertex1, Vertex vertex2) {
        return new Edge(edge.id, mirroredShape(edge.shape), vertex1, vertex2);
    }

    /**
     * Creates a mirrored shape being a reverse of existing shape.
     *
     * @param shape the shape to be mirrored represented as e.g. "11010"
     * @return a new mirrored shape.
     */
    public static String mirroredShape(String shape) {
        return new StringBuilder(shape).reverse().toString();
    }

    /**
     * Checks if the edge matches. If it does returns a factor of match which is a number of pairs of 0 on one side 1 on the other.
     *
     * @param edge the edge to matched.
     * @return -1 if there is a collision between edges or vertices.
     */
    public int matches(Edge edge) {
        int result = 0;
        int singleResult = 0;
        String shape = edge.shape;
        for (int i = 0; i < 3; i++) {
            singleResult = edgeValue(shape, i);
            if (singleResult == 2)
                return -1;
            result += singleResult;
        }
        singleResult = singleVertexValue(edge.getVertices(), 0);
        if (singleResult == 2) {
            return -1;
        }
        result += singleResult;
        singleResult = singleVertexValue(edge.getVertices(), 1);
        if (singleResult == 2) {
            return -1;
        }
        result += singleResult;
        return result;
    }

    /**
     * Joins a given Edge with an existing Edge by filling in 0 with 1.
     *
     * @param edge the edge to be joined with existing one.
     * @return with the new edge shape.
     */
    public String joinShapes(Edge edge) {
        String shape = edge.shape;
        char vertex1Shape = getVertexShape(edge.getVertices(), 0);
        char vertex2Shape = getVertexShape(edge.getVertices(), 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(singleShapeValue(this.shape, i) + singleShapeValue(shape, i));
        }
        if (vertex1Shape == '1') {
            vertices.get(0).setShape(vertex1Shape);
        }
        if (vertex2Shape == '1') {
            vertices.get(1).setShape(vertex2Shape);
        }
        this.shape = builder.toString();
        this.oppositeEdge.shape = builder.reverse().toString();
        return this.shape;
    }


    /**
     * Gets joined edge value for given id in the string of e.g. "00000"
     * @param shape the shape being tested.
     * @param index the id of existing shape and shape being joined from another edge of another block.
     * @return the sum of join. 0 - if both are empty, 1 - if one is empty and the other filled, 2 - if there is collision.
     */
    private int edgeValue(String shape, int index) {
        return singleShapeValue(this.shape, index) + singleShapeValue(shape, index);
    }

    /**
     * Values of single shape as integer.
     * @param shape the shape.
     * @param index the id of the shapes string.
     * @return the integer value.
     */
    private int singleShapeValue(String shape, int index) {
        return singleElementValue(shape.charAt(index));
    }

    /**
     * Translates character value to integer.
     * @param shape the shape's character.
     * @return the integer representation. e.g. '1' = 1
     */
    private int singleElementValue(char shape) {
        return shape == '1' ? 1 : 0;
    }

    /**
     * Gets joined single vertex value.
     * @param vertices the list of vertices.
     * @param index the id from vertices list.
     * @return 0 - if both the vertex of cube is empty and vertex of block being inserted is empty.
     *         1 - if one of vertices is filled and the other is empty.
     *         2 - if both vertices are filled and there is a collision.
     */
    private int singleVertexValue(List<Vertex> vertices, int index) {
        return singleElementValue(this.vertices.get(index).getShape()) + singleElementValue(vertices.get(index).getShape());
    }

    /**
     * Gets the joined character describing vertex after block's vertex was inserted into the cube.
     * @param vertices the list of vertices for given edge.
     * @param index the id of the vertex on the list.
     * @return character of the new state (shape) of vertex
     */
    private char getVertexShape(List<Vertex> vertices, int index) {
        return this.vertices.get(index).getShape() == '0' && vertices.get(index).getShape() == '0' ? '0' : '1';
    }

    /**
     * Clears the Edge from any values which changes all 1 to 0 including the vertices shapes.
     */
    public void clear() {
        this.shape = "000";
        this.vertices.stream().forEach(Vertex::clear);
    }

    /**
     * Gets the edge shape.
     * @return the edge shape excluding shape of vertices e.g. "101"
     */
    public String getShape() {
        return shape;
    }

    /**
     * Gets the edge full edge shape consisting of vertices shape and edge middle shape e.g. "11011".
     * @return string with full edge shape.
     */
    public String getFullShape() {
        return vertices.get(0).getShape() + shape + vertices.get(1).getShape();
    }

    /**
     * Gets list of shape vertices. List contains two elements.
     * Element 0 is vertex1 and element 1 is vertex2
     * @return list of vertices for the edge.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Prints the edge's shape.
     * @return the string representing edge's shape.
     */
    public String printEdge() {
        return getFullShape().replace("0", " ").replace("1", "O");
    }

    /**
     * Sets the opposite shape being the
     * @param oppositeEdge the opposite edge instance.
     */
    public void setOppositeEdge(Edge oppositeEdge) {
        this.oppositeEdge = oppositeEdge;
    }

    /**
     * Gets id for the given edge.
     * @return the edge's id.
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Edge" + id + " " + vertices.get(0).toString() + "-" + vertices.get(1).toString() + " " + getFullShape();
    }
}
