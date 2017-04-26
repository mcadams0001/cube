package adam.services;

import adam.dto.Block;
import adam.exceptions.CubeCreationExceptions;

import java.io.IOException;
import java.util.List;

/**
 * Main execution service.
 */
public interface ExecutionService {
    /**
     * Creates all possible solutions on how the cube can be build from given blocks.
     * @param fileName name of the file where the resolved cubes are to be stored.
     * @param blocks the set of blocks used for building the cube.
     */
    void createCubesFile(String fileName, List<Block> blocks) throws CubeCreationExceptions;
}
