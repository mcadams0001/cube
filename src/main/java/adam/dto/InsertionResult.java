package adam.dto;

/**
 * Represents block insertion result into the cube.
 * If the block is null then the insertion didn't succeed and density will be set to -1.
 */
public class InsertionResult {
    private Block block;
    private int density;

    public InsertionResult(Block block, int density) {
        this.block = block;
        this.density = density;
    }

    public Block getBlock() {
        return block;
    }

    public int getDensity() {
        return density;
    }
}
