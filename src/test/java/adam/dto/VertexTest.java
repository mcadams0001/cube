package adam.dto;

import adam.helpers.EqualsTestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VertexTest {

    @Test
    void createVertex() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point);
        assertEquals(1, vertex.getId());
        assertEquals(point, vertex.getPoint());
        assertEquals('0', vertex.getShape());
    }

    @Test
    void setVertexShape() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point);
        vertex.setShape('x');
        assertEquals('x', vertex.getShape());
        vertex.clear();
        assertEquals('0', vertex.getShape());
    }

    @Test
    void verifyBothVertexAreSame() {
        Point point = new PointXYZ(0, 0, 0);
        Point point2 = new PointXYZ(1, 0, 0);
        Vertex vertex = new Vertex(1, point);
        Vertex vertex2 = new Vertex(1, point);
        Vertex vertex3 = new Vertex(2, point);
        EqualsTestHelper.verifyEquals(vertex, vertex2, vertex3);
        assertNotEquals(new Vertex(1, point, '0'), new Vertex(1, point, 'x'));
        assertNotEquals(new Vertex(1, point2, 'x'), new Vertex(1, point, 'x'));
        assertNotEquals(new Vertex(1, null, 'x'), new Vertex(1, point, 'x'));

    }

    @Test
    void getHashCode() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point, '0');
        Vertex vertex2 = new Vertex(1, point, '0');
        Vertex vertex3 = new Vertex(2, point, 'X');
        EqualsTestHelper.verifyHashCode(vertex, vertex2, vertex3);
        EqualsTestHelper.verifyHashCode(vertex, vertex2, new Vertex(3, null));
    }

    @Test
    void getToString() {
        Point point = new PointXYZ(1, 0, 0);
        Vertex vertex = new Vertex(1, point, 'X');
        assertEquals("[1,0,0]=X", vertex.toString());
    }


}