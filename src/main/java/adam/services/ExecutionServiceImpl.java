package adam.services;

import adam.dto.Block;
import adam.dto.Cube;
import adam.exceptions.CubeCreationExceptions;
import adam.fixture.BlockFixture;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExecutionServiceImpl implements ExecutionService {

    private CubeService cubeService;

    public ExecutionServiceImpl(CubeService cubeService) {
        this.cubeService = cubeService;
    }

    @Override
    public void createCubesFile(String fileName, List<Block> blocks) throws CubeCreationExceptions {
        List<Cube> cubes = cubeService.createAllCubesFromBlocks(BlockFixture.getBlocks());
        writeCubesToFile(fileName, cubes);
    }

    private void writeCubesToFile(String fileName, List<Cube> cubes) throws CubeCreationExceptions {
        if (cubes.isEmpty()) {
            throw new CubeCreationExceptions("No cube could be created from given blocks");
        }
        try(FileWriter fileWriter = getFileWriter(fileName)) {
            for (Cube cube : cubes) {
                cube.printCube(fileWriter);
            }
            fileWriter.flush();
        } catch (IOException ex) {
            throw new CubeCreationExceptions(ex.getMessage(), ex);
        }
    }

    FileWriter getFileWriter(String fileName) throws IOException {
        return new FileWriter(fileName);
    }
}
