package adam.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationEnumTest {
    @Test
    void shouldRotationEnum() {
        String[] shapes = RotationEnum.DEG_90.rotateShapes(new String[]{"11010", "00100", "01010", "01011"});
        assertEquals("01011", shapes[0]);;
        assertEquals("11010", shapes[1]);;
        assertEquals("00100", shapes[2]);;
        assertEquals("01010", shapes[3]);;
    }


}