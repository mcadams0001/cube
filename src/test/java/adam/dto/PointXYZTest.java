package adam.dto;

import adam.helpers.EqualsTestHelper;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class PointXYZTest {
    @Test
    public void testVerifyEquals() {
        EqualsTestHelper.verifyEquals(new PointXYZ(1,1,1), new PointXYZ(1,1, 1), new PointXYZ(1,2,3));
        EqualsTestHelper.verifyEquals(new PointXYZ(1,1,1), new PointXYZ(1,1, 1), new PointXYZ(1,1,3));
        EqualsTestHelper.verifyEquals(new PointXYZ(1,1,1), new PointXYZ(1,1, 1), new PointXYZ(2,1,3));
    }

    @Test
    public void testVerifyHashCode() {
        EqualsTestHelper.verifyHashCode(new PointXYZ(1,1,1), new PointXYZ(1,1, 1), new PointXYZ(1,2,3));
    }

    @Test
    public void shouldVerifyGetters() {
        Point pointXY = new PointXYZ(1,2,3);
        assertThat(pointXY.getX(), equalTo(1));
        assertThat(pointXY.getY(), equalTo(2));
        assertThat(pointXY.getZ(), equalTo(3));
    }

    @Test
    public void shouldGetToString() {
        Point pointXY = new PointXYZ(1,5,10);
        assertThat(pointXY.toString(), equalTo("[1,5,10]"));
    }

}