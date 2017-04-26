package adam.dto;

import adam.enums.RotationEnum;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static adam.helpers.PrintUtil.printList;
import static adam.helpers.PrintUtil.printLists;


/*
Numbering of Vertices and Edges
    7------6-------6
    / |           /|
   /  |          / |
  7   11        5  |
 /    |        /   10
4-------4------5   |
 |  3 |       |    |
 |   / -----2-|---/ 2
 8  3         9  /
 | /          | 1
 |/           |/
 |-----0------|
 0            1
 */

/*
Numbering of blocks making the cube
                             ---------------
                            /             /|<-- invisible block 3
                           /  |          / |
    invisible block 4  -->/       5(top)/  |
                         /    |        /   |
                         --------------    |
                         |    |       | 2  |
                         |     - - - -|  -/
                         |  /    1    |  /
                         |            | /<-- 0 at the bottom
                         |/           |/
                         |------------|

 */
public class Cube {

    private List<CubeBlock> blocks = new ArrayList<>();

    public Cube(List<CubeBlock> blocks) {
        this.blocks = blocks;
    }

    public List<CubeBlock> getBlocks() {
        return blocks;
    }

    public CubeBlock getBlock(int index) {
        return blocks.get(index);
    }

    public void clear() {
        blocks.forEach(CubeBlock::clear);
    }


    /**
     * Prints an unfolded cube in the following form:
     * @param writer writer to which the block is supposed to be printed to.


            ------------------------------------
            |          |           |           |
            |    1     |     2     |     3     |
            |          |           |           |
            |          |           |           |
            ------------------------------------
                       |           |
                       |     0     |
                       |           |
                       |           |
                       -------------
                       |           |
                       |     4     |
                       |           |
                       |           |
                       -------------
                       |           |
                       |     5     |
                       |           |
                       |           |
                       -------------
     */

    public void printCube(Writer writer) throws IOException {
        Block cubeBlock4 = this.getBlock(1).getAssignedBlock();
        Block cubeBlock0 = this.getBlock(2).getAssignedBlock();
        Block cubeBlock2 = this.getBlock(3).getAssignedBlock();
        printLists(writer, cubeBlock4.printBlock(), cubeBlock0.printBlock(), cubeBlock2.printBlock());

        writer.write("\n");
        Block cubeBlock3 = this.getBlock(0).getAssignedBlock().mirrored().rotated(RotationEnum.DEG_270);
        printList(cubeBlock3.printBlock(), writer);

        writer.write("\n");
        Block cubeBlock5 = this.getBlock(4).getAssignedBlock().rotated(RotationEnum.DEG_180);
        printList(cubeBlock5.printBlock(), writer);

        writer.write("\n");

        Block cubeBlock1 = this.getBlock(5).getAssignedBlock().mirrored().rotated(RotationEnum.DEG_270);
        printList(cubeBlock1.printBlock(), writer);
        writer.write("\n\n");
    }

    void printCubeDebug(Writer writer) throws IOException {
        getBlocks().forEach(b -> {
            try {
                writer.write(b.getId() + "->" + b.getAssignedBlock().toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        printCube(writer);

    }



}
