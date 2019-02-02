package adam.factory;

import adam.dto.Cube;
import adam.dto.CubeBlock;
import adam.dto.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CubeFactoryTest {
    @Test
    void createInstance() {
        new CubeFactory();
        Cube cube = CubeFactory.createInstance();
        assertNotNull(cube);
        List<CubeBlock> blocks = cube.getBlocks();
        assertNotNull(blocks);
        assertEquals(6, blocks.size());
        assertFalse(blocks.stream().anyMatch(b -> b.getEdges().size() != 4));
        List<Edge> edges = blocks.stream().flatMap(b -> b.getEdges().stream()).collect(toList());
        assertEquals(24, edges.size());
        edges.forEach(edge -> {
            assertEquals(2, edge.getVertices().size());
            edge.getVertices().forEach(vertex -> assertNotNull(vertex.getPoint()));
        });
    }
}