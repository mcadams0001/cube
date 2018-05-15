package adam.dto;

import adam.enums.RotationEnum;
import adam.factory.CubeFactory;
import adam.fixture.BlockFixture;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CubeBlockTest {
    @Test
    void shouldJoinBlockWithCube() {
        Cube cube = CubeFactory.createInstance();
        Block block = BlockFixture.getBlock5();
        CubeBlock cubeBlock = cube.getBlock(0);
        int resultPlace = cubeBlock.insertBlock(block);
        assertEquals(11, resultPlace);
        Block block2 = BlockFixture.getBlock2().rotated(RotationEnum.DEG_270);
        int secondOperation = cube.getBlock(4).insertBlock(block2);
        assertEquals(8, secondOperation);
        resultPlace = cubeBlock.insertBlock(block);
        assertEquals(-1, resultPlace);
    }

    @Test
    void shouldReturnMirroredBlock() {
        //"11011", "11010", "01010", "00101"
        Block mirrored = BlockFixture.getBlock5().mirrored();
        assertEquals("11011", mirrored.getEdge(0).getFullShape());
        assertEquals("10100", mirrored.getEdge(1).getFullShape());
        assertEquals("01010", mirrored.getEdge(2).getFullShape());
        assertEquals("01011", mirrored.getEdge(3).getFullShape());
    }

    @Test
    void shouldReturnMirroredAndRotated() {
        Block block = BlockFixture.getBlock3().mirrored().rotated(RotationEnum.DEG_90);
        assertEquals("00100", block.getEdge(0).getFullShape());
        assertEquals("01011", block.getEdge(1).getFullShape());
        assertEquals("11010", block.getEdge(2).getFullShape());
        assertEquals("01010", block.getEdge(3).getFullShape());

    }


    @Test
    void shouldRotate() {
        Block block4 = BlockFixture.getBlock3().rotated(RotationEnum.DEG_90);
        assertEquals("01011", block4.getEdge(0).getFullShape());
        assertEquals("11010", block4.getEdge(1).getFullShape());
        assertEquals("00100", block4.getEdge(2).getFullShape());
        assertEquals("01010", block4.getEdge(3).getFullShape());

        //"11010", "00100", "01010", "01011"
    }

    @Test
    void shouldRotateBy90Deg() {
        Block block = BlockFixture.getBlock5().rotated(RotationEnum.DEG_90);
        assertEquals("00101", block.getEdge(0).getFullShape());
        assertEquals("11011", block.getEdge(1).getFullShape());
        assertEquals("11010", block.getEdge(2).getFullShape());
        assertEquals("01010", block.getEdge(3).getFullShape());
    }

    @Test
    void shouldRotateBy180Deg() {
        Block block = BlockFixture.getBlock5().rotated(RotationEnum.DEG_180);
        assertEquals("01010", block.getEdge(0).getFullShape());
        assertEquals("00101", block.getEdge(1).getFullShape());
        assertEquals("11011", block.getEdge(2).getFullShape());
        assertEquals("11010", block.getEdge(3).getFullShape());
    }

    @Test
    void shouldRotateBy270Deg() {
        Block block = BlockFixture.getBlock5().rotated(RotationEnum.DEG_270);
        assertEquals("11010", block.getEdge(0).getFullShape());
        assertEquals("01010", block.getEdge(1).getFullShape());
        assertEquals("00101", block.getEdge(2).getFullShape());
        assertEquals("11011", block.getEdge(3).getFullShape());
    }

    @Test
    void shouldPlaceCubes() {
        Cube cube = CubeFactory.createInstance();
        CubeBlock block0 = cube.getBlock(0);
        CubeBlock block1 = cube.getBlock(1);
        CubeBlock block2 = cube.getBlock(2);
        CubeBlock block3 = cube.getBlock(3);

        int block0inserted = block0.insertBlock(BlockFixture.getBlock5());
        assertEquals(11, block0inserted, "Failed to insert block 0");
        assertShapeForEdge(block0.getEdges(), "11011", "11010", "01010", "00101");
        assertVertices(block0.getEdges(), '1', '1', '1', '0', '0', '0', '0', '1');
        assertShapeForEdge(block1.getEdges(), "01010", "00000", "00000", "00000");
        assertVertices(block1.getEdges(), '0', '0', '0', '0', '0', '0', '0', '0');
        assertShapeForEdge(block2.getEdges(), "01011", "10000", "00000", "00000");
        assertVertices(block2.getEdges(), '0', '1', '1', '0', '0', '0', '0', '0');


        int block1inserted = block1.insertBlock(BlockFixture.getBlock1());
        assertEquals(16, block1inserted, "Failed to insert block 1");
        assertShapeForEdge(block0.getEdges(), "11011", "11011", "11111", "10101");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11011", "10101", "11011");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11011", "10000", "00001", "11011");
        assertVertices(block2.getEdges(), '1', '1', '1', '0', '0', '1', '1', '1');


        int block2inserted = block2.insertBlock(BlockFixture.getBlock0());
        assertEquals(14, block2inserted, "Failed to insert block 2");
        assertShapeForEdge(block0.getEdges(), "11011", "11111", "11111", "10101");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11111", "10101", "11011");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11111", "10100", "00101", "11111");
        assertVertices(block2.getEdges(), '1', '1', '1', '0', '0', '1', '1', '1');


        Block block3ToPlace = BlockFixture.getBlock3().mirrored().rotated(RotationEnum.DEG_90);
        int block3inserted = block3.insertBlock(block3ToPlace);
        assertEquals(16, block3inserted, "Failed to insert block 3");
        assertShapeForEdge(block0.getEdges(), "11111", "11111", "11111", "10101");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11111", "10101", "11011");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11111", "11110", "00101", "11111");
        assertVertices(block2.getEdges(), '1', '1', '1', '0', '0', '1', '1', '1');
        assertShapeForEdge(block3.getEdges(), "11111", "11011", "11010", "01111");
        assertVertices(block3.getEdges(), '1', '1', '1', '1', '1', '0', '0', '1');

        CubeBlock block4 = cube.getBlock(4);
        int block4inserted = block4.insertBlock(BlockFixture.getBlock2().rotated(RotationEnum.DEG_270));
        assertEquals(18, block4inserted, "Failed to insert block 4");
        assertShapeForEdge(block0.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11111", "10101", "11111");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11111", "11110", "00101", "11111");
        assertVertices(block2.getEdges(), '1', '1', '1', '0', '0', '1', '1', '1');
        assertShapeForEdge(block3.getEdges(), "11111", "11111", "11010", "01111");
        assertVertices(block3.getEdges(), '1', '1', '1', '1', '1', '0', '0', '1');
        assertShapeForEdge(block4.getEdges(), "11111", "11111", "10101", "11111");
        assertVertices(block4.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');

        CubeBlock block5 = cube.getBlock(5);
        int block5inserted = block5.insertBlock(BlockFixture.getBlock4().rotated(RotationEnum.DEG_180));
        assertEquals(20, block5inserted, "Failed to insert block 5");
        assertShapeForEdge(block0.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block2.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block3.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block3.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block4.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block4.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block5.getEdges(), "11111", "11111", "11111", "11111");
        assertVertices(block5.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');

    }

    @Test
    void shouldIsSymmetric() {
        assertTrue(BlockFixture.getBlock0().isSymmetric());
        assertFalse(BlockFixture.getBlock3().isSymmetric());
    }

    @Test
    void shouldCountDensityOfBlock() {
        assertEquals(4, BlockFixture.getBlock0().density());
        assertEquals(11, BlockFixture.getBlock5().density());
    }

    @Test
    void shouldIsOneAxisSymmetric() {
        assertTrue(BlockFixture.getBlock0().isOneAxisSymmetric());
        assertTrue(BlockFixture.getBlock1().isOneAxisSymmetric());
        assertFalse(BlockFixture.getBlock2().isOneAxisSymmetric());
    }

    @Test
    void shouldInsertBlock() throws IOException {
        /*
        0->Block3 DEG_0
        1->Block1 DEG_0
        2->Block2 DEG_270
        3->Block5 DEG_270 Mirrored
        4->Block0 DEG_0
        5->Block4 DEG_180 Mirrored
         */

        Cube cube = CubeFactory.createInstance();
        assertEquals(9, cube.getBlock(0).insertBlock(BlockFixture.getBlock3()));
        assertEquals(16, cube.getBlock(1).insertBlock(BlockFixture.getBlock1()));
        assertEquals(12, cube.getBlock(2).insertBlock(BlockFixture.getBlock2().rotated(RotationEnum.DEG_270)));
        assertEquals(16, cube.getBlock(3).insertBlock(BlockFixture.getBlock5().mirrored().rotated(RotationEnum.DEG_270)));
        assertEquals(16, cube.getBlock(4).insertBlock(BlockFixture.getBlock0()));
        assertEquals(20, cube.getBlock(5).insertBlock(BlockFixture.getBlock4().mirrored().rotated(RotationEnum.DEG_180)));

        StringWriter writer = new StringWriter();
        cube.printCubeDebug(writer);
        System.out.println(writer.toString());
    }


    private void assertVertices(List<Edge> edges, char... shapes) {
        int index = 0;
        for (Edge edge : edges) {
            assertEquals(shapes[index], edge.getVertices().get(0).getShape(), "Invalid id in vertex 0 for: " + index);
            index++;
            assertEquals(shapes[index], edge.getVertices().get(1).getShape(), "Invalid id in vertex 1 for: " + index);
            index++;
        }
    }

    private void assertShapeForEdge(List<Edge> edges, String... shapes) {
        for (int i = 0; i < shapes.length; i++) {
            assertEquals(shapes[i], edges.get(i).getFullShape(), "Invalid edge for id: " + i);
        }
    }


}