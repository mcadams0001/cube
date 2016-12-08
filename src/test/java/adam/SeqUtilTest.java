package adam;

import adam.helpers.SeqUtil;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SeqUtilTest {

    @Test
    public void testCreateNextSequence() throws Exception {
        Queue nextSequence = SeqUtil.createNextSequence("012345");
        assertThat(nextSequence.size(), equalTo(720));
    }

    @Test
    public void shouldRemoveAllRepresentationsForWhichResultWasFound() throws Exception {
        String representation = "012345";
        Queue<String> set = new LinkedList<>();
        set.addAll(Arrays.asList("012345", "234501", "134250", "034521"));
        SeqUtil.removeSameRepresentations(set, representation);
        assertThat(set, notNullValue());
        assertThat(set, hasItems("234501"));

    }

}