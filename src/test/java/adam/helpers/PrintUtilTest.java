package adam.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintUtilTest {
    @Mock
    private Writer mockWriter;

    @Test
    public void testShouldPrintList() throws Exception {
        new PrintUtil();
        List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F");
        PrintUtil.printList(list, mockWriter);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockWriter, times(12)).write(captor.capture());
        List<String> actual = captor.getAllValues();
        assertThat(actual.size(), equalTo(list.size()*2));
        assertThat(actual.get(0), equalTo("      "));
        assertThat(actual.get(1), equalTo("A\n"));
    }

}