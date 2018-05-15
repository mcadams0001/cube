package adam.dto;

import adam.fixture.CubeFixture;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeTest {

    @Test
    void testClear() {
        Cube cube = CubeFixture.createInstance();
        assertEquals("11111", cube.getBlock(0).getEdge(0).getFullShape());
        assertEquals("11111", cube.getBlock(1).getEdge(1).getFullShape());
        assertEquals("11111", cube.getBlock(2).getEdge(2).getFullShape());
        assertEquals("11111", cube.getBlock(3).getEdge(3).getFullShape());
        assertEquals("11111", cube.getBlock(4).getEdge(0).getFullShape());
        assertEquals("11111", cube.getBlock(5).getEdge(1).getFullShape());
        cube.clear();
        assertEquals("00000", cube.getBlock(0).getEdge(0).getFullShape());
        assertEquals("00000", cube.getBlock(1).getEdge(1).getFullShape());
        assertEquals("00000", cube.getBlock(2).getEdge(2).getFullShape());
        assertEquals("00000", cube.getBlock(3).getEdge(3).getFullShape());
        assertEquals("00000", cube.getBlock(4).getEdge(0).getFullShape());
        assertEquals("00000", cube.getBlock(5).getEdge(1).getFullShape());

    }

    @Test
    void testPrintCube() throws IOException {
        Cube cube = CubeFixture.createInstance();
        StringWriter writer = new StringWriter();
        cube.printCube(writer);
        System.out.println(writer.getBuffer().toString());
    }
}