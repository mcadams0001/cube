package adam.dto;

import java.util.List;

public class CubeBlock extends Block {

    private Block assignedBlock;

    public CubeBlock(int index, List<Edge> edges) {
        super(index, edges);
    }

    /**
     * Inserts a block in the cube.
     * @param block the block to be placed in the cube.
     * @return density (how well the block fills the spaces after insertion, number of edges having 1 in their shape after insertion).
     *         -1 is returned if the block doesn't fit into the space in the cube.
     */
    public int insertBlock(Block block) {
        int placement = validatePlacement(block);
        if(placement == -1) {
            return -1;
        }
        joinBlockShapes(block);
        return placement;
    }

    /**
     * Validates the given block if it fit's into the space represented by cube block (this).
     * @param block the block to be validated whether it can be inserted into the given space.
     * @return -1 if space is occupied or if block doesn't fit otherwise the density
     *          (how well the block fills the spaces after insertion, number of edges having 1 in their shape after insertion).
     */
    public int validatePlacement(Block block) {
        if (isOccupied()) {
            return -1;
        }
        return blockMatches(block);
    }

    /**
     * Joins shapes between given block and empty space in the cube represented by this.
     * @param block the block to be joined with the given space in the cube.
     */
    public void joinBlockShapes(Block block) {
        for (int i = 0; i < TOTAL_EDGES; i++) {
            getEdge(i).joinShapes(block.getEdge(i));
        }
        assignedBlock = block;
    }

    /**
     * Checks whether the given block matches the space in the cube.
     * @param block the block to be checked whether it fits the space in the cube.
     * @return -1 if block doesn't fit otherwise the density (how well the block fills the spaces after insertion, number of edges having 1 in their shape after insertion).
     */
    public int blockMatches(Block block) {
        int matchValue = 0;
        for (int i = 0; i < TOTAL_EDGES; i++) {
            int singleMatchValue = getEdge(i).matches(block.getEdge(i));
            if (singleMatchValue == -1) {
                return -1;
            }
            matchValue += singleMatchValue;
        }
        return matchValue;
    }

    /**
     * Clears the space in the block by cleaning all edges(4) and vertices(4).
     */
    public void clear() {
        edges.stream().forEach(Edge::clear);
        assignedBlock = null;
    }

    /**
     * Gets the reference to the block that was inserted into the cube.
     * @return the reference to assigned block.
     */
    public Block getAssignedBlock() {
        return assignedBlock;
    }

    /**
     * Checks whether the block is occupied by checking if there is an assigned block.
     * @return true if occupied otherwise false.
     */
    public boolean isOccupied() {
        return assignedBlock != null;
    }


}
