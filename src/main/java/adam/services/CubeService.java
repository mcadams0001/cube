package adam.services;

import adam.dto.Block;
import adam.dto.Cube;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Service used for building cube from blocks.
 */
public interface CubeService {
    /**
     * Creates a cube from blocks by inserting given blocks into the cube and finding the once that fit the cube.
     * @param blocks the set of blocks used for building the cube.
     * @return a new instance of cube if successfully built otherwise null.
     */
    Cube createCubeFromBlocks(List<Block> blocks);

    /**
     * Creates all possible solutions on how the cube can be build from given blocks.
     * @param blocks the set of blocks used for building the cube.
     * @return a list of new instances of cubes build from given blocks.
     */
    List<Cube> createAllCubesFromBlocks(List<Block> blocks);
}
