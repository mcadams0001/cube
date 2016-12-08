package adam.dto;

import adam.enums.RotationEnum;
import adam.factory.CubeFactory;
import adam.fixture.BlockFixture;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class EdgeTest {

    @Test
    public void shouldMatchTwoWalls() throws Exception {
        Edge edge = BlockFixture.getBlock5().getEdge(0);
        Edge edge2 = BlockFixture.getBlock3().rotated(RotationEnum.DEG_270).getEdge(0);
        int matches = edge.matches(edge2);
        assertThat(matches, equalTo(5));
    }

    @Test
    public void shouldNotMatchTwoWalls() throws Exception {
        Edge edge = BlockFixture.getBlock0().getEdge(0);
        Edge edge2 = BlockFixture.getBlock0().getEdge(0);
        int matches = edge.matches(edge2);
        assertThat(matches, equalTo(-1));
    }

    @Test
    public void shouldJoinTwoEdges() throws Exception {
        Cube cube = CubeFactory.createInstance();
        CubeBlock cubeBlock = cube.getBlock(0);
        Edge edge = cubeBlock.getEdge(0);
        Edge blockEdge = BlockFixture.getBlock0().getEdge(0);
        String joinedEdge = edge.joinShapes(blockEdge);
        assertThat(joinedEdge, equalTo(blockEdge.getShape()));
    }

    @Test
    public void shouldFailToJoinShapesIfVertexIsOccupied() throws Exception {
        Cube cube = CubeFactory.createInstance();
        CubeBlock cubeBlock = cube.getBlock(0);
        Edge edge = cubeBlock.getEdge(0);
        edge.getVertices().get(0).setShape('1');
        Edge blockEdge = BlockFixture.getBlock1().getEdge(0);
        int result = edge.matches(blockEdge);
        assertThat(result, equalTo(-1));
    }

    @Test
    public void shouldMirrorEdge() throws Exception {
        //"11100"
        Vertex node0 = new Vertex(0, new PointXYZ(0, 0, 0), '1');
        Vertex node1 = new Vertex(1, new PointXYZ(1, 0, 0), '0');
        Edge edge = new Edge(0, "110", node0, node1);

        Edge mirroredEdge = Edge.mirroredEdge(edge, node1, node0);
        assertThat(mirroredEdge.getShape(), equalTo("011"));
        assertThat(mirroredEdge.getVertices().get(0).getShape(), equalTo('0'));
        assertThat(mirroredEdge.getVertices().get(1).getShape(), equalTo('1'));
    }


}