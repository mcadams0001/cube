package adam.dto;

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
        assertThat(vertex.equals(vertex2), equalTo(true));
        assertThat(vertex.equals(vertex), equalTo(true));
        assertThat(vertex.equals(null), equalTo(false));
        assertThat(vertex.equals("test"), equalTo(false));
    }

    @Test
    public void getHashCode() {
        Point point = new PointXYZ(0, 0, 0);
        Vertex vertex = new Vertex(1, point, '0');
        Vertex vertex2 = new Vertex(2, point, 'X');
        assertThat(vertex.hashCode(), not(equalTo(vertex2.hashCode())));
        assertThat(new Vertex(3,null).hashCode(), equalTo(2931));
    }

    @Test
    public void getToString() {
        Point point = new PointXYZ(1, 0, 0);
        Vertex vertex = new Vertex(1, point, 'X');
        assertThat(vertex.toString(), equalTo("[1,0,0]=X"));
    }


}