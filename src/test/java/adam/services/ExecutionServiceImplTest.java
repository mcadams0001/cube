package adam.services;

import adam.dto.Block;
import adam.dto.Cube;
import adam.exceptions.CubeCreationExceptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
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
        List<Cube> cubes = Arrays.asList(mockCube, mockCube, mockCube, mockCube);
        doReturn(mockFileWriter).when(spyService).getFileWriter(anyString());
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        spyService.createCubesFile("fileName", blocks);
        verify(mockCube, times(4)).printCube(mockFileWriter);
        verify(mockFileWriter).flush();
    }

    @Test(expected = CubeCreationExceptions.class)
    public void testReturnWithoutWritingFileIfCubesAreEmpty() throws IOException, CubeCreationExceptions {
        List<Block> blocks = new ArrayList<>();
        List<Cube> cubes = new ArrayList<>();
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        service.createCubesFile("fileName", blocks);
    }

    @Test
    public void testShouldGet() throws IOException {
        URL url = ExecutionServiceImpl.class.getResource(".");
        File targetFolder = new File(url.getFile());
        assertThat(targetFolder, notNullValue());
        try (FileWriter fileWriter = service.getFileWriter(targetFolder.getAbsolutePath() + File.separator + "sampleFile.txt")) {
            assertThat(fileWriter, notNullValue());
        }
    }

    @Test
    public void testCaptureIOException() throws Exception {
        Cube mockCube2 = mock(Cube.class);
        ExecutionServiceImpl spyService = spy(service);
        List<Block> blocks = new ArrayList<>();
        List<Cube> cubes = Arrays.asList(mockCube, mockCube, mockCube2);
        doReturn(mockFileWriter).when(spyService).getFileWriter(anyString());
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        doThrow(new IOException("test")).when(mockCube2).printCube(any());
        try {
            spyService.createCubesFile("fileName", blocks);
            fail("Expected CubeCreationException");
        } catch (CubeCreationExceptions ex) {
            //Do nothing.
        }
        verify(mockCube, times(2)).printCube(mockFileWriter);
        verify(mockFileWriter, never()).flush();
    }
}