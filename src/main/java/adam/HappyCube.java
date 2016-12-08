package adam;

import adam.dto.Cube;
import adam.fixture.BlockFixture;
import adam.services.CubeService;
import adam.services.CubeServiceImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HappyCube {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide filename for the result file");
            return;
        }
        CubeService service = new CubeServiceImpl();
        List<Cube> cubes = service.createAllCubesFromBlocks(BlockFixture.getBlocks());
        writeCubesToFile(args[0], cubes);
    }

    private static void writeCubesToFile(String fileName, List<Cube> cubes) throws IOException {
        if (cubes.size() == 0) {
            System.out.println("No cube could be created from given blocks");
            return;
        }
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            for (Cube cube : cubes) {
                cube.printCube(fileWriter);
            }
            fileWriter.flush();
        }
    }
}
