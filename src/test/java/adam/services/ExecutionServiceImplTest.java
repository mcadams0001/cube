package adam.services;

import adam.dto.Block;
import adam.dto.Cube;
import adam.exceptions.CubeCreationExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExecutionServiceImplTest {

    private ExecutionServiceImpl service;

    @Mock
    private CubeService mockCubeService;

    @Mock
    private FileWriter mockFileWriter;

    @Mock
    private Cube mockCube;

    @BeforeEach
    void setup() {
        service = new ExecutionServiceImpl(mockCubeService);
    }

    @Test
    void createCubesFile() throws Exception {
        ExecutionServiceImpl spyService = spy(service);
        List<Block> blocks = new ArrayList<>();
        List<Cube> cubes = Arrays.asList(mockCube, mockCube, mockCube, mockCube);
        doReturn(mockFileWriter).when(spyService).getFileWriter(anyString());
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        spyService.createCubesFile("fileName", blocks);
        verify(mockCube, times(4)).printCube(mockFileWriter);
        verify(mockFileWriter).flush();
    }

    @Test
    void testReturnWithoutWritingFileIfCubesAreEmpty() {
        List<Block> blocks = new ArrayList<>();
        List<Cube> cubes = new ArrayList<>();
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        assertThrows(CubeCreationExceptions.class, () -> service.createCubesFile("fileName", blocks));
    }

    @Test
    void testShouldGet() throws IOException {
        URL url = ExecutionServiceImpl.class.getResource(".");
        File targetFolder = new File(url.getFile());
        assertNotNull(targetFolder);
        try (FileWriter fileWriter = service.getFileWriter(targetFolder.getAbsolutePath() + File.separator + "sampleFile.txt")) {
            assertNotNull(fileWriter);
        }
    }

    @Test
    void testCaptureIOException() throws Exception {
        Cube mockCube2 = mock(Cube.class);
        ExecutionServiceImpl spyService = spy(service);
        List<Block> blocks = new ArrayList<>();
        List<Cube> cubes = Arrays.asList(mockCube, mockCube, mockCube2);
        doReturn(mockFileWriter).when(spyService).getFileWriter(anyString());
        when(mockCubeService.createAllCubesFromBlocks(anyList())).thenReturn(cubes);
        doThrow(new IOException("test")).when(mockCube2).printCube(any());
        assertThrows(CubeCreationExceptions.class, () -> spyService.createCubesFile("fileName", blocks));
        verify(mockCube, times(2)).printCube(mockFileWriter);
        verify(mockFileWriter, never()).flush();
    }
}