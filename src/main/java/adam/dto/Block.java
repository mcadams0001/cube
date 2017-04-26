package adam.dto;

import adam.enums.RotationEnum;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * A block being a set of edges and vertices.
 */
public class Block {

    static final int TOTAL_EDGES = 4;

    /**
     * The id of the block, which should be kept unique.
     */
    private int id;
    /**
     * The set of 4 edges describing the block.
     */
    List<Edge> edges;
    /**
     * Map holding reference to all rotated blocks of this block.
     */
    private Map<RotationEnum, Block> rotatedBlocks = new HashMap<>();
    /**
     * Reference to mirrored block of this block.
     */
    private Block mirroredBlock;
    /**
     * Flag set to true if this block is mirrored otherwise false.
     */
    private boolean mirrored;
    /**
     * The rotation of this block, by default 0 degree.
     */
    private RotationEnum rotated = RotationEnum.DEG_0;

    Block(int id, List<Edge> edges) {
        this.id = id;
        this.edges = edges;
    }

    /**
     * Creates a new instance of block.
     * @param id block's unique id.
     * @param shapes array holding a set of shapes representing the block.
     *               The array describes shapes going clockwise from bottom left corner to bottom right,
     *               to upper right corner, to upper left corner and back to bottom left corner.
     * @return a new instance of block.
     */
    public static Block createInstance(int id, String[] shapes) {
        Vertex vertex1 = new Vertex(0, new PointXY(0, 0));
        Vertex vertex2 = new Vertex(1, new PointXY(1, 0));
        Vertex vertex3 = new Vertex(2, new PointXY(1, 1));
        Vertex vertex4 = new Vertex(3, new PointXY(0, 1));

        vertex1.setShape(shapes[0].charAt(0));
        vertex2.setShape(shapes[0].charAt(4));
        vertex3.setShape(shapes[1].charAt(4));
        vertex4.setShape(shapes[2].charAt(4));

        Edge edge1 = new Edge(0, shapes[0].substring(1, 4), vertex1, vertex2);
        Edge edge2 = new Edge(1, shapes[1].substring(1, 4), vertex2, vertex3);
        Edge edge3 = new Edge(2, shapes[2].substring(1, 4), vertex3, vertex4);
        Edge edge4 = new Edge(3, shapes[3].substring(1, 4), vertex4, vertex1);

        Block block = new Block(id, Arrays.asList(edge1, edge2, edge3, edge4));
        block.rotatedBlocks.put(RotationEnum.DEG_0, block);
        return block;
    }

    /**
     * Creates a new block from given one being a Y axis mirror of existing block.
     * @param block the block to be mirrored.
     * @return a new instance of block being the mirror in Y axis of the original one.
     */
    private static Block createMirroredBlock(Block block) {
        String[] rotatedShapes = new String[]{
                Edge.mirroredShape(block.getEdge(0).getFullShape()),
                Edge.mirroredShape(block.getEdge(3).getFullShape()),
                Edge.mirroredShape(block.getEdge(2).getFullShape()),
                Edge.mirroredShape(block.getEdge(1).getFullShape())};
        Block newBlock = Block.createInstance(block.getId(), rotatedShapes);
        newBlock.rotated = block.rotated;
        newBlock.mirrored = true;
        return newBlock;
    }

    /**
     * Creates a new instance of block being rotated by 90, 180 or 270 degree.
     * @param block the original block to be rotated.
     * @param rotationEnum the enumeration describing the degree of rotation.
     * @return a new instance of block being a rotated version of original one.
     */
    private static Block createRotatedBlock(Block block, RotationEnum rotationEnum) {
        String[] rotatedShapes = rotationEnum.rotateShapes(new String[]{
                block.getEdge(0).getFullShape(),
                block.getEdge(1).getFullShape(),
                block.getEdge(2).getFullShape(),
                block.getEdge(3).getFullShape()});
        Block newBlock = Block.createInstance(block.getId(), rotatedShapes);
        newBlock.rotated = rotationEnum;
        newBlock.mirrored = block.mirrored;
        return newBlock;
    }

    /**
     * Counts the number of filled spaces in the shape of the block.
     * The space is represented by number of 0 and 1. The count reflects the number of 1 in the shape.
     * @param shape the shape for which filled spaces are to be counted.
     * @return the total number of 1 in the shape.
     */
    private static int countFilledSpaces(String shape) {
        return (int) shape.chars().filter(c -> c == '1').count();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    Edge getEdge(int index) {
        return edges.get(index);
    }

    public int getId() {
        return id;
    }

    /**
     * Prints the block using "O" where there is a 1 on the edge or space if 0.
     *
     * @return List of lines making up the printed block
     */
    List<String> printBlock() {
        List<String> printList = new ArrayList<>();
        printList.add(edges.get(2).printEdge());
        for (int i = 0; i < TOTAL_EDGES - 1; i++) {
            printList.add(stateToShape(edges.get(3).getShape().charAt(i)) + "OOO" + stateToShape(edges.get(1).getShape().charAt(i)));
        }
        printList.add(edges.get(0).printEdge());
        return printList;
    }

    public char getCharIndex() {
        return Character.forDigit(id, 10);
    }

    private String stateToShape(char shape) {
        return shape == '1' ? "O" : " ";
    }

    public Block rotated(RotationEnum rotationDeg) {
        return rotatedBlocks.computeIfAbsent(rotationDeg, rotationEnum -> Block.createRotatedBlock(this, rotationEnum));
    }

    public Block mirrored() {
        if (mirroredBlock == null) {
            mirroredBlock = Block.createMirroredBlock(this);
        }
        return mirroredBlock;
    }

    /**
     * Checks whether the given block is fully symmetric.
     * @return true if fully symmetric in both X and Y axis otherwise false.
     */
    boolean isSymmetric() {
        List<String> fullEdges = edgeShapes();
        return fullEdges.stream().allMatch(Predicate.isEqual(fullEdges.get(0)));
    }

    /**
     * Checks whether the given block is symmetric in one axis by verifying the full shapes of edges.
     * @return true if symmetric otherwise false.
     */
    public boolean isOneAxisSymmetric() {
        List<String> fullEdges = edgeShapes();
        return fullEdges.get(0).equals(fullEdges.get(2)) && fullEdges.get(1).equals(fullEdges.get(3));
    }

    /**
     * Calculates the density (how well the block fills the spaces after insertion, number of edges having 1 in their shape after insertion).
     * @return number of 1 on the edge of the block.
     */
    int density() {
        List<String> fullEdges = edgeShapes();
        return fullEdges.stream().mapToInt(Block::countFilledSpaces).sum();
    }

    /**
     * Gets a list of all full shapes for every edge in the block.
     * @return list of strings representing shapes.
     */
    private List<String> edgeShapes() {
        return edges.stream().map(Edge::getFullShape).collect(toList());
    }

    @Override
    public String toString() {
        return "Block" + id + " " + rotated.name() + (mirrored ? " Mirrored" : "");
    }
}
