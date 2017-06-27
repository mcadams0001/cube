package adam.dto;

import adam.helpers.EqualsTestHelper;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class PointXYTest {
    @Test
    public void testVerifyEquals() {
        EqualsTestHelper.verifyEquals(new PointXY(1,1), new PointXY(1,1), new PointXY(1,2));
        EqualsTestHelper.verifyEquals(new PointXY(1,1), new PointXY(1,1), new PointXY(2,1));
    }

    @Test
    public void testVerifyHashCode() {
        EqualsTestHelper.verifyHashCode(new PointXY(1,1), new PointXY(1,1), new PointXY(5,10));
    }

    @Test
    public void shouldVerifyGetters() {
        Point pointXY = new PointXY(1,2);
        assertThat(pointXY.getX(), equalTo(1));
        assertThat(pointXY.getY(), equalTo(2));
        assertThat(pointXY.getZ(), equalTo(0));
    }

    @Test
    public void shouldGetToString() {
        Point pointXY = new PointXY(1,5);
        assertThat(pointXY.toString(), equalTo("[1,5]"));
    }

}