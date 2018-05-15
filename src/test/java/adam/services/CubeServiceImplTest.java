package adam.services;

import adam.dto.Block;
import adam.dto.Cube;
import adam.dto.CubeBlock;
import adam.fixture.BlockFixture;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CubeServiceImplTest {

    private CubeServiceImpl service = new CubeServiceImpl();

    @Test
    void testAssignBlocksToCube() throws Exception {
        List<Block> blockList = new ArrayList<>();
        blockList.add(BlockFixture.getBlock0());
        blockList.add(BlockFixture.getBlock1());
        blockList.add(BlockFixture.getBlock2());
        blockList.add(BlockFixture.getBlock3());
        blockList.add(BlockFixture.getBlock4());
        blockList.add(BlockFixture.getBlock5());
        Cube cube = service.createCubeFromBlocks(blockList);
        assertNotNull(cube);
        assertEquals(6L, cube.getBlocks().stream().filter(CubeBlock::isOccupied).count());
        cube.getBlocks().forEach(b -> System.out.println(b.getId() + "->" + b.getAssignedBlock().toString()));
        StringWriter writer = new StringWriter();
        cube.printCube(writer);
        System.out.println(writer.toString());
    }

    @Test
    void shouldGetAllCubeResults() throws Exception {
        List<Block> blockList = new ArrayList<>();
        blockList.add(BlockFixture.getBlock0());
        blockList.add(BlockFixture.getBlock1());
        blockList.add(BlockFixture.getBlock2());
        blockList.add(BlockFixture.getBlock3());
        blockList.add(BlockFixture.getBlock4());
        blockList.add(BlockFixture.getBlock5());
        List<Cube> cubes = service.createAllCubesFromBlocks(blockList);
        assertEquals(5, cubes.size());
        StringWriter writer = new StringWriter();
        for (Cube cube : cubes) {
            cube.getBlocks().forEach(b -> writer.write(b.getId() + "->" + b.getAssignedBlock().toString() + "\n"));
            cube.printCube(writer);
        }
        System.out.println(writer.toString());
    }
}