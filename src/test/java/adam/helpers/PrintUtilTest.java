package adam.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrintUtilTest {
    @Mock
    private Writer mockWriter;

    @Test
    void testShouldPrintList() throws Exception {
        new PrintUtil();
        List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F");
        PrintUtil.printList(list, mockWriter);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockWriter, times(12)).write(captor.capture());
        List<String> actual = captor.getAllValues();
        assertEquals(list.size() * 2, actual.size());
        assertEquals("      ", actual.get(0));
        assertEquals("A\n", actual.get(1));
    }

}