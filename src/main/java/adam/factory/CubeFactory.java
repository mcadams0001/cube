package adam.factory;

import adam.dto.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Factory used for creating a new instance of a cube.
 */
public class CubeFactory {
    /**
     * Creates a new instance of cube.
     * @return new instance of cube.
     */
    public static Cube createInstance() {
        Vertex node0 = new Vertex(0, new PointXYZ(0, 0, 0));
        Vertex node1 = new Vertex(1, new PointXYZ(1, 0, 0));
        Vertex node2 = new Vertex(2, new PointXYZ(1, 0, 1));
        Vertex node3 = new Vertex(3, new PointXYZ(0, 0, 1));

        Vertex node4 = new Vertex(4, new PointXYZ(0, 1, 0));
        Vertex node5 = new Vertex(5, new PointXYZ(1, 1, 0));
        Vertex node6 = new Vertex(6, new PointXYZ(1, 1, 1));
        Vertex node7 = new Vertex(7, new PointXYZ(0, 1, 1));

        Edge edge01 = new Edge(0, node0, node1);
        Edge edge10 = new Edge(0, node1, node0);
        edge01.setOppositeEdge(edge10);
        edge10.setOppositeEdge(edge01);

        Edge edge12 = new Edge(1, node1, node2);
        Edge edge21 = new Edge(1, node2, node1);
        edge12.setOppositeEdge(edge21);
        edge21.setOppositeEdge(edge12);

        Edge edge23 = new Edge(2, node2, node3);
        Edge edge32 = new Edge(2, node3, node2);
        edge23.setOppositeEdge(edge32);
        edge32.setOppositeEdge(edge23);

        Edge edge30 = new Edge(3, node3, node0);
        Edge edge03 = new Edge(3, node0, node3);
        edge30.setOppositeEdge(edge03);
        edge03.setOppositeEdge(edge30);

        Edge edge45 = new Edge(4, node4, node5);
        Edge edge54 = new Edge(4, node5, node4);
        edge45.setOppositeEdge(edge54);
        edge54.setOppositeEdge(edge45);

        Edge edge56 = new Edge(5, node5, node6);
        Edge edge65 = new Edge(5, node6 ,node5);
        edge56.setOppositeEdge(edge65);
        edge65.setOppositeEdge(edge56);

        Edge edge67 = new Edge(6, node6, node7);
        Edge edge76 = new Edge(6, node7, node6);
        edge67.setOppositeEdge(edge76);
        edge76.setOppositeEdge(edge67);

        Edge edge74 = new Edge(7, node7, node4);
        Edge edge47 = new Edge(7, node4, node7);
        edge74.setOppositeEdge(edge47);
        edge47.setOppositeEdge(edge74);

        Edge edge04 = new Edge(8, node0, node4);
        Edge edge40 = new Edge(8, node4, node0);
        edge04.setOppositeEdge(edge40);
        edge40.setOppositeEdge(edge04);

        Edge edge15 = new Edge(9, node1, node5);
        Edge edge51 = new Edge(9, node5, node1);
        edge15.setOppositeEdge(edge51);
        edge51.setOppositeEdge(edge15);

        Edge edge26 = new Edge(10, node2, node6);
        Edge edge62 = new Edge(10, node6, node2);
        edge26.setOppositeEdge(edge62);
        edge62.setOppositeEdge(edge26);

        Edge edge37 = new Edge(11, node3, node7);
        Edge edge73 = new Edge(11, node7, node3);
        edge37.setOppositeEdge(edge73);
        edge73.setOppositeEdge(edge37);

        CubeBlock block1 = new CubeBlock(0, Arrays.asList(edge32, edge21, edge10, edge03));
        CubeBlock block2 = new CubeBlock(1, Arrays.asList(edge01, edge15, edge54, edge40));
        CubeBlock block3 = new CubeBlock(2, Arrays.asList(edge12, edge26, edge65, edge51));
        CubeBlock block4 = new CubeBlock(3, Arrays.asList(edge23, edge37, edge76, edge62));
        CubeBlock block5 = new CubeBlock(4, Arrays.asList(edge30, edge04, edge47, edge73));
        CubeBlock block6 = new CubeBlock(5, Arrays.asList(edge45, edge56, edge67, edge74));

        return new Cube(new ArrayList<>(Arrays.asList(block1, block2, block3, block4, block5, block6)));
    }
}
