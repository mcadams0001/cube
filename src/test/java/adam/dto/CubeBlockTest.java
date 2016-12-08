package adam.dto;

import adam.enums.RotationEnum;
import adam.factory.CubeFactory;
import adam.fixture.BlockFixture;
import org.junit.Test;

import java.io.StringWriter;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CubeBlockTest {
    @Test
    public void shouldJoinBlockWithCube() throws Exception {
        Cube cube = CubeFactory.createInstance();
        Block block = BlockFixture.getBlock5();
        CubeBlock cubeBlock = cube.getBlock(0);
        int resultPlace = cubeBlock.insertBlock(block);
        assertThat(resultPlace, equalTo(11));
        Block block2 = BlockFixture.getBlock2().rotated(RotationEnum.DEG_270);
        int secondOperation = cube.getBlock(4).insertBlock(block2);
        assertThat(secondOperation, equalTo(8));
        resultPlace = cubeBlock.insertBlock(block);
        assertThat(resultPlace, equalTo(-1));
    }

    @Test
    public void shouldReturnMirroredBlock() throws Exception {
        //"11011", "11010", "01010", "00101"
        Block mirrored = BlockFixture.getBlock5().mirrored();
        assertThat(mirrored.getEdge(0).getFullShape(), equalTo("11011"));
        assertThat(mirrored.getEdge(1).getFullShape(), equalTo("10100"));
        assertThat(mirrored.getEdge(2).getFullShape(), equalTo("01010"));
        assertThat(mirrored.getEdge(3).getFullShape(), equalTo("01011"));
    }

    @Test
    public void shouldReturnMirroredAndRotated() throws Exception {
        Block block = BlockFixture.getBlock3().mirrored().rotated(RotationEnum.DEG_90);
        assertThat(block.getEdge(0).getFullShape(), equalTo("00100"));
        assertThat(block.getEdge(1).getFullShape(), equalTo("01011"));
        assertThat(block.getEdge(2).getFullShape(), equalTo("11010"));
        assertThat(block.getEdge(3).getFullShape(), equalTo("01010"));

    }


    @Test
    public void shouldRotate() throws Exception {
        Block block4 = BlockFixture.getBlock3().rotated(RotationEnum.DEG_90);
        assertThat(block4.getEdge(0).getFullShape(), equalTo("01011"));
        assertThat(block4.getEdge(1).getFullShape(), equalTo("11010"));
        assertThat(block4.getEdge(2).getFullShape(), equalTo("00100"));
        assertThat(block4.getEdge(3).getFullShape(), equalTo("01010"));

        //"11010", "00100", "01010", "01011"
    }

    @Test
    public void shouldRotateBy90Deg() throws Exception {
        Block block = BlockFixture.getBlock5().rotated(RotationEnum.DEG_90);
        assertThat(block.getEdge(0).getFullShape(), equalTo("00101"));
        assertThat(block.getEdge(1).getFullShape(), equalTo("11011"));
        assertThat(block.getEdge(2).getFullShape(), equalTo("11010"));
        assertThat(block.getEdge(3).getFullShape(), equalTo("01010"));
    }

    @Test
    public void shouldRotateBy180Deg() throws Exception {
        Block block = BlockFixture.getBlock5().rotated(RotationEnum.DEG_180);
        assertThat(block.getEdge(0).getFullShape(), equalTo("01010"));
        assertThat(block.getEdge(1).getFullShape(), equalTo("00101"));
        assertThat(block.getEdge(2).getFullShape(), equalTo("11011"));
        assertThat(block.getEdge(3).getFullShape(), equalTo("11010"));
    }

    @Test
    public void shouldRotateBy270Deg() throws Exception {
        Block block = BlockFixture.getBlock5().rotated(RotationEnum.DEG_270);
        assertThat(block.getEdge(0).getFullShape(), equalTo("11010"));
        assertThat(block.getEdge(1).getFullShape(), equalTo("01010"));
        assertThat(block.getEdge(2).getFullShape(), equalTo("00101"));
        assertThat(block.getEdge(3).getFullShape(), equalTo("11011"));
    }

    @Test
    public void shouldPlaceCubes() throws Exception {
        Cube cube = CubeFactory.createInstance();
        CubeBlock block0 = cube.getBlock(0);
        CubeBlock block1 = cube.getBlock(1);
        CubeBlock block2 = cube.getBlock(2);
        CubeBlock block3 = cube.getBlock(3);

        int block0inserted = block0.insertBlock(BlockFixture.getBlock5());
        assertThat("Failed to insert block 0", block0inserted, equalTo(11));
        assertShapeForEdge(block0.getEdges(), "11011", "11010", "01010", "00101");
        assertVertices(block0.getEdges(), '1', '1', '1', '0', '0', '0', '0', '1');
        assertShapeForEdge(block1.getEdges(), "01010", "00000", "00000", "00000");
        assertVertices(block1.getEdges(), '0', '0', '0', '0', '0', '0', '0', '0');
        assertShapeForEdge(block2.getEdges(), "01011", "10000", "00000", "00000");
        assertVertices(block2.getEdges(), '0', '1', '1', '0', '0', '0', '0', '0');


        int block1inserted = block1.insertBlock(BlockFixture.getBlock1());
        assertThat("Failed to insert block 1", block1inserted, equalTo(16));
        assertShapeForEdge(block0.getEdges(), "11011", "11011", "11111", "10101");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11011", "10101", "11011");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11011", "10000", "00001", "11011");
        assertVertices(block2.getEdges(), '1', '1', '1', '0', '0', '1', '1', '1');


        int block2inserted = block2.insertBlock(BlockFixture.getBlock0());
        assertThat("Failed to insert block 2", block2inserted, equalTo(14));
        assertShapeForEdge(block0.getEdges(), "11011", "11111", "11111", "10101");
        assertVertices(block0.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block1.getEdges(), "11111", "11111", "10101", "11011");
        assertVertices(block1.getEdges(), '1', '1', '1', '1', '1', '1', '1', '1');
        assertShapeForEdge(block2.getEdges(), "11111", "10100", "00101", "11111");
        assertVertices(block2.getEdges(), '1', '1', '1', '0', '0', '1', '1', '1');


        Block block3ToPlace = BlockFixture.getBlock3().mirrored().rotated(RotationEnum.DEG_90);
        int block3inserted = block3.insertBlock(block3ToPlace);
        assertThat("Failed to insert block 3", block3inserted, equalTo(16));
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
        assertThat("Failed to insert block 4", block4inserted, equalTo(18));
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
        assertThat("Failed to insert block 5", block5inserted, equalTo(20));
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
    public void shouldIsSymmetric() throws Exception {
        assertThat(BlockFixture.getBlock0().isSymmetric(), equalTo(true));
        assertThat(BlockFixture.getBlock3().isSymmetric(), equalTo(false));
    }

    @Test
    public void shouldCountDensityOfBlock() throws Exception {
        assertThat(BlockFixture.getBlock0().density(), equalTo(4));
        assertThat(BlockFixture.getBlock5().density(), equalTo(11));
    }

    @Test
    public void shouldIsOneAxisSymmetric() throws Exception {
        assertThat(BlockFixture.getBlock0().isOneAxisSymmetric(), equalTo(true));
        assertThat(BlockFixture.getBlock1().isOneAxisSymmetric(), equalTo(true));
        assertThat(BlockFixture.getBlock2().isOneAxisSymmetric(), equalTo(false));
    }

    @Test
    public void shouldInsertBlock() throws Exception {
        /*
        0->Block3 DEG_0
        1->Block1 DEG_0
        2->Block2 DEG_270
        3->Block5 DEG_270 Mirrored
        4->Block0 DEG_0
        5->Block4 DEG_180 Mirrored
         */

        Cube cube = CubeFactory.createInstance();
        assertThat(cube.getBlock(0).insertBlock(BlockFixture.getBlock3()), equalTo(9));
        assertThat(cube.getBlock(1).insertBlock(BlockFixture.getBlock1()), equalTo(16));
        assertThat(cube.getBlock(2).insertBlock(BlockFixture.getBlock2().rotated(RotationEnum.DEG_270)), equalTo(12));
        assertThat(cube.getBlock(3).insertBlock(BlockFixture.getBlock5().mirrored().rotated(RotationEnum.DEG_270)), equalTo(16));
        assertThat(cube.getBlock(4).insertBlock(BlockFixture.getBlock0()), equalTo(16));
        assertThat(cube.getBlock(5).insertBlock(BlockFixture.getBlock4().mirrored().rotated(RotationEnum.DEG_180)), equalTo(20));

        StringWriter writer = new StringWriter();
        cube.printCubeDebug(writer);
        System.out.println(writer.toString());
    }



    private void assertVertices(List<Edge> edges, char... shapes) {
        int index = 0;
        for (Edge edge : edges) {
            assertThat("Invalid id in vertex 0 for: " + index, edge.getVertices().get(0).getShape(), equalTo(shapes[index]));
            index++;
            assertThat("Invalid id in vertex 1 for: " + index, edge.getVertices().get(1).getShape(), equalTo(shapes[index]));
            index++;
        }
    }

    public void assertShapeForEdge(List<Edge> edges, String... shapes) {
        for (int i = 0; i < shapes.length; i++) {
            assertThat("Invalid edge for id: " + i, edges.get(i).getFullShape(), equalTo(shapes[i]));
        }
    }


}