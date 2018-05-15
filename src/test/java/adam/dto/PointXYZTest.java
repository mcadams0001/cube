package adam.dto;

import adam.helpers.EqualsTestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PointXYZTest {
    @Test
    void testVerifyEquals() {
        EqualsTestHelper.verifyEquals(new PointXYZ(1, 1, 1), new PointXYZ(1, 1, 1), new PointXYZ(1, 2, 3));
        EqualsTestHelper.verifyEquals(new PointXYZ(1, 1, 1), new PointXYZ(1, 1, 1), new PointXYZ(1, 1, 3));
        EqualsTestHelper.verifyEquals(new PointXYZ(1, 1, 1), new PointXYZ(1, 1, 1), new PointXYZ(2, 1, 3));
    }

    @Test
    void testVerifyHashCode() {
        EqualsTestHelper.verifyHashCode(new PointXYZ(1, 1, 1), new PointXYZ(1, 1, 1), new PointXYZ(1, 2, 3));
    }

    @Test
    void shouldVerifyGetters() {
        Point pointXY = new PointXYZ(1, 2, 3);
        assertEquals(1, pointXY.getX());
        assertEquals(2, pointXY.getY());
        assertEquals(3, pointXY.getZ());
    }

    @Test
    void shouldGetToString() {
        Point pointXY = new PointXYZ(1, 5, 10);
        assertEquals("[1,5,10]", pointXY.toString());
    }

}