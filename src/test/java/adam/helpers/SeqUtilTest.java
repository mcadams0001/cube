package adam.helpers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class SeqUtilTest {

    @Test
    void testCreateNextSequence() {
        new SeqUtil();
        Queue nextSequence = SeqUtil.createNextSequence("012345");
        assertEquals(720, nextSequence.size());
    }

    @Test
    void shouldRemoveAllRepresentationsForWhichResultWasFound() {
        String representation = "012345";
        Queue<String> set = new LinkedList<>(Arrays.asList("012345", "234501", "134250", "034521"));
        SeqUtil.removeSameRepresentations(set, representation);
        assertNotNull(set);
        assertTrue(set.contains("234501"));

    }

}