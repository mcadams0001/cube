package adam.enums;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Adam on 16/05/2015.
 */
public class RotationEnumTest {
    @Test
    public void shouldRotationEnum() throws Exception {
        String[] shapes = RotationEnum.DEG_90.rotateShapes(new String[]{"11010", "00100", "01010", "01011"});
        assertThat(shapes[0], equalTo("01011"));
        assertThat(shapes[1], equalTo("11010"));
        assertThat(shapes[2], equalTo("00100"));
        assertThat(shapes[3], equalTo("01010"));
    }


}