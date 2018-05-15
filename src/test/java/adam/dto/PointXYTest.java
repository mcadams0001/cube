package adam.dto;

import adam.helpers.EqualsTestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointXYTest {
    @Test
    void testVerifyEquals() {
        EqualsTestHelper.verifyEquals(new PointXY(1, 1), new PointXY(1, 1), new PointXY(1, 2));
        EqualsTestHelper.verifyEquals(new PointXY(1, 1), new PointXY(1, 1), new PointXY(2, 1));
    }

    @Test
    void testVerifyHashCode() {
        EqualsTestHelper.verifyHashCode(new PointXY(1, 1), new PointXY(1, 1), new PointXY(5, 10));
    }

    @Test
    void shouldVerifyGetters() {
        Point pointXY = new PointXY(1, 2);
        assertEquals(1, pointXY.getX());
        assertEquals(2, pointXY.getY());
        assertEquals(0, pointXY.getZ());
    }

    @Test
    void shouldGetToString() {
        Point pointXY = new PointXY(1, 5);
        assertEquals("[1,5]", pointXY.toString());
    }

}