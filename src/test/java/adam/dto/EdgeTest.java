package adam.dto;

import adam.enums.RotationEnum;
import adam.factory.CubeFactory;
import adam.fixture.BlockFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeTest {

    @Test
    void shouldMatchTwoWalls() {
        Edge edge = BlockFixture.getBlock5().getEdge(0);
        Edge edge2 = BlockFixture.getBlock3().rotated(RotationEnum.DEG_270).getEdge(0);
        int matches = edge.matches(edge2);
        assertEquals(5, matches);
        assertEquals(0, edge.getId());
        assertEquals("Edge0 [0,0]=1-[1,0]=1 11011", edge.toString());
    }

    @Test
    void shouldNotMatchTwoWalls() {
        Edge edge = BlockFixture.getBlock0().getEdge(0);
        Edge edge2 = BlockFixture.getBlock0().getEdge(0);
        int matches = edge.matches(edge2);
        assertEquals(-1, matches);
    }

    @Test
    void shouldJoinTwoEdges() {
        Cube cube = CubeFactory.createInstance();
        CubeBlock cubeBlock = cube.getBlock(0);
        Edge edge = cubeBlock.getEdge(0);
        Edge blockEdge = BlockFixture.getBlock0().getEdge(0);
        String joinedEdge = edge.joinShapes(blockEdge);
        assertEquals(blockEdge.getShape(), joinedEdge);
    }

    @Test
    void shouldFailToJoinShapesIfVertexIsOccupied() {
        Cube cube = CubeFactory.createInstance();
        CubeBlock cubeBlock = cube.getBlock(0);
        Edge edge = cubeBlock.getEdge(0);
        edge.getVertices().get(0).setShape('1');
        Edge blockEdge = BlockFixture.getBlock1().getEdge(0);
        int result = edge.matches(blockEdge);
        assertEquals(-1, result);
    }

    @Test
    void shouldMirrorEdge() {
        //"11100"
        Vertex node0 = new Vertex(0, new PointXYZ(0, 0, 0), '1');
        Vertex node1 = new Vertex(1, new PointXYZ(1, 0, 0), '0');
        Edge edge = new Edge(0, "110", node0, node1);

        Edge mirroredEdge = Edge.mirroredEdge(edge, node1, node0);
        assertEquals("011", mirroredEdge.getShape());
        assertEquals('0', mirroredEdge.getVertices().get(0).getShape());
        assertEquals('1', mirroredEdge.getVertices().get(1).getShape());
    }


}