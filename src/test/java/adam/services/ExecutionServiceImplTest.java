package adam.services;

import adam.dto.Block;
import adam.dto.Cube;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionServiceImplTest {

    private ExecutionServiceImpl service;

    @Mock
    private CubeService mockCubeService;

    @Mock
    private FileWriter mockFileWriter;

    @Mock
    private Cube mockCube;

    @Before
    public void setup() throws Exception {
        service = new ExecutionServiceImpl(mockCubeService);
    }

    @Test
    public void createCubesFile() throws Exception {
        ExecutionServiceImpl spyService = spy(service);
        List<Block> blocks = new ArrayList<>();
        List<Cube> cubes = Arrays.asList(mockCube,mockCube,mockCube,mockCube);
        doReturn(mockFileWriter).when(spyService).getFileWriter(anyString());
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        spyService.createCubesFile("fileName", blocks);
        verify(mockCube, times(4)).printCube(mockFileWriter);
        verify(mockFileWriter).flush();
    }
}