package adam.dto;

import adam.fixture.CubeFixture;
import org.junit.Test;

import java.io.StringWriter;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CubeTest {

    @Test
    public void testClear() throws Exception {
        Cube cube = CubeFixture.createInstance();
        assertThat(cube.getBlock(0).getEdge(0).getFullShape(), equalTo("11111"));
        assertThat(cube.getBlock(1).getEdge(1).getFullShape(), equalTo("11111"));
        assertThat(cube.getBlock(2).getEdge(2).getFullShape(), equalTo("11111"));
        assertThat(cube.getBlock(3).getEdge(3).getFullShape(), equalTo("11111"));
        assertThat(cube.getBlock(4).getEdge(0).getFullShape(), equalTo("11111"));
        assertThat(cube.getBlock(5).getEdge(1).getFullShape(), equalTo("11111"));
        cube.clear();
        assertThat(cube.getBlock(0).getEdge(0).getFullShape(), equalTo("00000"));
        assertThat(cube.getBlock(1).getEdge(1).getFullShape(), equalTo("00000"));
        assertThat(cube.getBlock(2).getEdge(2).getFullShape(), equalTo("00000"));
        assertThat(cube.getBlock(3).getEdge(3).getFullShape(), equalTo("00000"));
        assertThat(cube.getBlock(4).getEdge(0).getFullShape(), equalTo("00000"));
        assertThat(cube.getBlock(5).getEdge(1).getFullShape(), equalTo("00000"));

    }

    @Test
    public void testPrintCube() throws Exception {
        Cube cube = CubeFixture.createInstance();
        StringWriter writer = new StringWriter();
        cube.printCube(writer);
        System.out.println(writer.getBuffer().toString());
    }
}