package adam.helpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class PrintUtilTest {
    @Mock
    private Writer mockWriter;

    @BeforeEach
    void setup() {
        initMocks(this);
    }

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