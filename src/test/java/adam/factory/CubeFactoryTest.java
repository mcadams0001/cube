package adam.factory;

import adam.dto.Cube;
import adam.dto.CubeBlock;
import adam.dto.Edge;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CubeFactoryTest {
    @Test
    public void createInstance() throws Exception {
        Cube cube = CubeFactory.createInstance();
        assertThat(cube, notNullValue());
        List<CubeBlock> blocks = cube.getBlocks();
        assertThat(blocks, notNullValue());
        assertThat(blocks.size(), equalTo(6));
        assertThat(blocks.stream().anyMatch(b -> b.getEdges().size() != 4), equalTo(false));
        List<Edge> edges = blocks.stream().flatMap(b -> b.getEdges().stream()).collect(toList());
        assertThat(edges.size(), equalTo(24));
        edges.forEach(edge -> {
            assertThat(edge.getVertices().size(), equalTo(2));
            edge.getVertices().forEach(vertex -> {
                assertThat(vertex.getPoint(), notNullValue());
                assertThat(vertex.getId(), notNullValue());
            });

        });

    }

}