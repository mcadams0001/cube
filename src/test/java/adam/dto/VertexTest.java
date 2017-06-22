package adam.dto;

import adam.helpers.EqualsTestHelper;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class VertexTest {

    @Test
    public void createVertex() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point);
        assertThat(vertex.getId(), equalTo(1));
        assertThat(vertex.getPoint(), equalTo(point));
        assertThat(vertex.getShape(), equalTo('0'));
    }

    @Test
    public void setVertexShape() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point);
        vertex.setShape('x');
        assertThat(vertex.getShape(), equalTo('x'));
        vertex.clear();
        assertThat(vertex.getShape(), equalTo('0'));
    }

    @Test
    public void verifyBothVertexAreSame() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point);
        Vertex vertex2 = new Vertex(1, point);
        Vertex vertex3 = new Vertex(2, point);
        EqualsTestHelper.verifyEquals(vertex, vertex2, vertex3);
    }

    @Test
    public void getHashCode() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point, '0');
        Vertex vertex2 = new Vertex(1, point, '0');
        Vertex vertex3 = new Vertex(2, point, 'X');
        EqualsTestHelper.verifyHashCode(vertex, vertex2, vertex3);
    }

    @Test
    public void getToString() {
        Point point = new PointXYZ(1, 0, 0);
        Vertex vertex = new Vertex(1, point, 'X');
        assertThat(vertex.toString(), equalTo("[1,0,0]=X"));
    }


}